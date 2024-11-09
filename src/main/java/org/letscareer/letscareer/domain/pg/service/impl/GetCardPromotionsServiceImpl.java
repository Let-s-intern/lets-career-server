package org.letscareer.letscareer.domain.pg.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.pg.dto.response.CardPromotionResponseDto;
import org.letscareer.letscareer.domain.pg.service.GetCardPromotionsService;
import org.letscareer.letscareer.global.common.utils.toss.TossSecretKeyGenerator;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class GetCardPromotionsServiceImpl implements GetCardPromotionsService {
    private String TOSS_REQUEST_URL = "https://api.tosspayments.com/v1";
    private final TossSecretKeyGenerator tossSecretKeyGenerator;

    @Override
    public CardPromotionResponseDto execute() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            String authorization = tossSecretKeyGenerator.generateApiSecretKey();
            headers.set("Authorization", authorization);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>("", headers);
            ResponseEntity<CardPromotionResponseDto> response = restTemplate.exchange(TOSS_REQUEST_URL + "/promotions/card", HttpMethod.GET, entity, CardPromotionResponseDto.class);
            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }
    }
}
