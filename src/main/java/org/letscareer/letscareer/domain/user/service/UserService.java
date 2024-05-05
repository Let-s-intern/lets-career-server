package org.letscareer.letscareer.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.user.domain.User;
import org.letscareer.letscareer.domain.user.helper.UserHelper;
import org.letscareer.letscareer.domain.user.mapper.UserMapper;
import org.letscareer.letscareer.domain.user.domain.AuthProvider;
import org.letscareer.letscareer.global.security.oauth2.userinfo.OAuth2UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserHelper userHelper;
    private final UserMapper userMapper;

    public User createUserFromOAuth2(OAuth2UserInfo oAuth2UserInfo, AuthProvider authProvider) {
        User newUser = userMapper.toEntityFromOAuth2(oAuth2UserInfo, authProvider);
        return userHelper.saveUserAndReturn(newUser);
    }

    public User updateUserFromOAuth2(User user, OAuth2UserInfo oAuth2UserInfo) {
        return user.updateFromOAuth2(oAuth2UserInfo);
    }
}
