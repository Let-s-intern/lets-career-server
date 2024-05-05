package org.letscareer.letscareer.global.security.oauth2;

import org.letscareer.letscareer.global.security.oauth2.userinfo.GoogleOAuth2UserInfo;
import org.letscareer.letscareer.global.security.oauth2.userinfo.KakaoOAuth2UserInfo;
import org.letscareer.letscareer.global.security.oauth2.userinfo.NaverOAuth2UserInfo;
import org.letscareer.letscareer.global.security.oauth2.userinfo.OAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(AuthProvider authProvider, Map<String, Object> attributes) {
        switch (authProvider) {
            case KAKAO -> {
                return new KakaoOAuth2UserInfo(attributes);
            }
            case NAVER -> {
                return new NaverOAuth2UserInfo(attributes);
            }
            case GOOGLE -> {
                return new GoogleOAuth2UserInfo(attributes);
            }

            default -> throw new IllegalArgumentException("Invalid Provider Type");
        }
    }
}
