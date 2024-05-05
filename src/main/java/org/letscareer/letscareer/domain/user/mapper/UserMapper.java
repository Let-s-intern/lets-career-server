package org.letscareer.letscareer.domain.user.mapper;

import org.letscareer.letscareer.domain.user.domain.User;
import org.letscareer.letscareer.global.security.oauth2.AuthProvider;
import org.letscareer.letscareer.global.security.oauth2.userinfo.OAuth2UserInfo;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntityFromOAuth2(OAuth2UserInfo oAuth2UserInfo, AuthProvider authProvider) {
        return User.createUserFromOAuth2(oAuth2UserInfo, authProvider);
    }
}
