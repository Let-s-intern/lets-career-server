package org.letscareer.letscareer.global.common.utils;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.program.dto.request.CreateZoomMeetingRequestDto;
import org.letscareer.letscareer.domain.program.dto.response.ZoomMeetingResponseDto;
import org.letscareer.letscareer.global.common.entity.ZoomAuthResponse;
import org.letscareer.letscareer.global.error.exception.InternalServerException;
import org.letscareer.letscareer.global.error.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.letscareer.letscareer.global.error.GlobalErrorCode.ZOOM_CREATE_INTERNAL_SERVER;
import static org.letscareer.letscareer.global.error.GlobalErrorCode.ZOOM_CREATE_TOKEN_ERROR;

@RequiredArgsConstructor
@Component
public class ZoomUtils {
    @Value("${spring.zoom.oauth2.client-id}")
    private String zoomClientId;
    @Value("${spring.zoom.oauth2.client-secret}")
    private String zoomClientSecret;
    @Value("${spring.zoom.oauth2.issuer}")
    private String zoomIssueUrl;
    @Value("${spring.zoom.oauth2.account-id}")
    private String zoomAccountId;
    @Value("${spring.zoom.oauth2.api-uri}")
    private String zoomApiUri;
    @Value("${spring.zoom.email.host}")
    private String hostEmail;

    private ZoomAuthResponse zoomAuthResponse;
    private long tokenExpiryTime;

    public synchronized String getAccessToken() {
        if (this.zoomAuthResponse == null || checkIfTokenWillExpire()) {
            fetchToken();
        }
        return this.zoomAuthResponse.accessToken();
    }

    /* 토큰 재발급이 필요한지 여부 확인 */
    private boolean checkIfTokenWillExpire() {
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
        long differenceInMillis = this.tokenExpiryTime - now.getTimeInMillis();

        // 토큰 이미 만료 or 20분내 만료 예정
        return differenceInMillis < 0 || TimeUnit.MILLISECONDS.toMinutes(differenceInMillis) < 20;
    }

    private void fetchToken() {
        RestTemplate restTemplate = new RestTemplate();

        String credentials = zoomClientId + ":" + zoomClientSecret;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_FORM_URLENCODED));
        httpHeaders.add("Authorization", "Basic " + encodedCredentials);
        httpHeaders.add("Host", "zoom.us");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "account_credentials");
        map.add("account_id", zoomAccountId);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, httpHeaders);
        try {
            this.zoomAuthResponse = restTemplate.exchange(zoomIssueUrl, HttpMethod.POST, httpEntity, ZoomAuthResponse.class).getBody();
        } catch (HttpClientErrorException e) {
            throw new UnauthorizedException(ZOOM_CREATE_TOKEN_ERROR);
        }

        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
        this.tokenExpiryTime = now.getTimeInMillis() + (this.zoomAuthResponse.expiresIn() - 10) * 1000;
    }

    public ZoomMeetingResponseDto createZoomMeeting(String title, LocalDateTime startDate) {
        CreateZoomMeetingRequestDto requestDTO = CreateZoomMeetingRequestDto.of(title, 180, startDate, title);
        String requestUrl = zoomApiUri + "/v2/users/" + hostEmail + "/meetings";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + getAccessToken());
        httpHeaders.add("content-type", "application/json");

        HttpEntity<CreateZoomMeetingRequestDto> httpEntity = new HttpEntity<>(requestDTO, httpHeaders);
        try {
            ResponseEntity<ZoomMeetingResponseDto> responseEntity = restTemplate.exchange(requestUrl, HttpMethod.POST, httpEntity, ZoomMeetingResponseDto.class);
            if (responseEntity.getStatusCode().value() == 201) {
                return responseEntity.getBody();
            }
        } catch (HttpClientErrorException e) {
            throw new InternalServerException(ZOOM_CREATE_INTERNAL_SERVER);
        }

        return null;
    }
}
