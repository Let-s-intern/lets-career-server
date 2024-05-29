package org.letscareer.letscareer.domain.user.service;

import org.letscareer.letscareer.domain.user.dto.request.*;
import org.letscareer.letscareer.domain.user.dto.response.TokenResponseDto;
import org.letscareer.letscareer.domain.user.dto.response.UserAdminListResponseDto;
import org.letscareer.letscareer.domain.user.dto.response.UserInfoResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.type.AuthProvider;
import org.letscareer.letscareer.global.security.oauth2.userinfo.OAuth2UserInfo;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User createUserFromOAuth2(OAuth2UserInfo oAuth2UserInfo, AuthProvider authProvider);

    User updateUserFromOAuth2(User user, OAuth2UserInfo oAuth2UserInfo);

    void pwSignUp(UserPwSignUpRequestDto pwSignUpRequestDto);

    TokenResponseDto pwSignIn(UserPwSignInRequestDto pwSignInRequestDto);

    void updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto);

    UserInfoResponseDto getUserInfo(User user);

    void deleteUser(User user);

    Boolean isAdmin(User user);

    UserAdminListResponseDto getUsers(Pageable pageable);

    void resetPassword(PasswordResetRequestDto passwordResetRequestDto);

    void updatePassword(Long id, PasswordUpdateRequestDto passwordUpdateRequestDto);
}
