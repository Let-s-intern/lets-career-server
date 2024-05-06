package org.letscareer.letscareer.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.user.dto.request.UserPwSignUpRequestDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.helper.UserHelper;
import org.letscareer.letscareer.domain.user.mapper.UserMapper;
import org.letscareer.letscareer.domain.user.type.AuthProvider;
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
        return userHelper.saveUser(newUser);
    }

    public User updateUserFromOAuth2(User user, OAuth2UserInfo oAuth2UserInfo) {
        return user.updateFromOAuth2(oAuth2UserInfo);
    }

    public void pwSignUp(UserPwSignUpRequestDto pwSignUpRequestDto) {
        userHelper.validateExistingUser(pwSignUpRequestDto.phoneNum());
        String encodedPassword = userHelper.encodePassword(pwSignUpRequestDto.password());
        User newUser = userMapper.toEntity(pwSignUpRequestDto, encodedPassword);
        userHelper.saveUser(newUser);
    }
}
