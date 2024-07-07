package org.letscareer.letscareer.global.security.oauth2.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.user.dto.response.OAuth2TokenResponseDto;
import org.letscareer.letscareer.global.common.utils.redis.CookieUtils;
import org.letscareer.letscareer.global.security.jwt.TokenProvider;
import org.letscareer.letscareer.global.security.oauth2.CookieAuthorizationRequestRepository;
import org.letscareer.letscareer.global.security.user.PrincipalDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${spring.security.oauth2.authorizedRedirectUri}")
    private String redirectUri;

    @Value("${spring.security.oauth2.authorizedRedirectUri-test}")
    private String redirectTestUri;

    @Value("${spring.security.oauth2.authorizedRedirectUri-local}")
    private String redirectLocalUri;
    private final TokenProvider tokenProvider;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl;
        if(((PrincipalDetails) authentication.getPrincipal()).isDuplicateUser()) {
            targetUrl = determineTargetUrlException(request, response);
        } else {
            targetUrl = determineTargetUrl(request, response, authentication);
        }

        if(response.isCommitted()) {
            logger.debug("Response has already been committed");
            return;
        }

        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String getRedirectUri(HttpServletRequest request, HttpServletResponse response) {
        Optional<String> redirectUri = CookieUtils.getCookie(request, CookieAuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);

        if(redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new RuntimeException("Redirect URIs are not matched");
        }

        return redirectUri.orElse(getDefaultTargetUrl());
    }
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String targetUrl = getRedirectUri(request, response);
        Boolean isNew = ((PrincipalDetails) authentication.getPrincipal()).isNew();
        OAuth2TokenResponseDto tokenResponse = OAuth2TokenResponseDto.of(
                tokenProvider.createOAuth2AccessToken(authentication),
                tokenProvider.createOAuth2RefreshToken(authentication),
                isNew
        );

        try {
            String result = objectMapper.writeValueAsString(tokenResponse);
            return UriComponentsBuilder.fromHttpUrl(targetUrl)
                    .queryParam("result", result)
                    .build().toUriString();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    protected String determineTargetUrlException(HttpServletRequest request, HttpServletResponse response) {
        String targetUrl = getRedirectUri(request, response);

        try {
            String error = objectMapper.writeValueAsString("already_signed_up_user");
            return UriComponentsBuilder.fromHttpUrl(targetUrl)
                    .queryParam("error", error)
                    .build().toUriString();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        cookieAuthorizationRequestRepository.removeAuthorizationRequest(request, response);
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);
        URI authorizedUri = URI.create(redirectUri);
        URI authorizedTestUri = URI.create(redirectTestUri);
        URI authorizedLocalUri = URI.create(redirectLocalUri);
        return isAuthorized(authorizedUri, clientRedirectUri) || isAuthorized(authorizedTestUri, clientRedirectUri) || isAuthorized(authorizedLocalUri, clientRedirectUri);
    }

    private boolean isAuthorized(URI authorizedUri, URI redirectUri) {
        return equalsHostIgnoreCase(authorizedUri, redirectUri) && equalsPort(authorizedUri, redirectUri);
    }

    private boolean equalsHostIgnoreCase(URI authorizedUri, URI redirectUri) {
        return authorizedUri.getHost().equalsIgnoreCase(redirectUri.getHost());
    }

    private boolean equalsPort(URI authorizedUri, URI redirectUri) {
        return authorizedUri.getPort() == redirectUri.getPort();
    }
}
