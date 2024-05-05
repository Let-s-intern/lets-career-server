package org.letscareer.letscareer.global.security.oauth2.userinfo;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

    private Long id;

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super((Map<String, Object>) attributes.get("kakao_account"));
        this.id = (Long) attributes.get("id");
    }

    @Override
    public String getOAuth2Id() {
        return this.id.toString();
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getPhoneNum() {
        return "0" + ((String) attributes.get("phone_number")).substring(4);
    }
}
