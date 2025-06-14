package org.letscareer.letscareer.domain.user.service;

import org.letscareer.letscareer.domain.application.dto.response.GetMyApplicationsResponseDto;
import org.letscareer.letscareer.domain.application.type.ApplicationStatus;
import org.letscareer.letscareer.domain.user.dto.request.*;
import org.letscareer.letscareer.domain.user.dto.response.*;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.type.AuthProvider;
import org.letscareer.letscareer.global.security.oauth2.userinfo.OAuth2UserInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User createUserFromOAuth2(OAuth2UserInfo oAuth2UserInfo, AuthProvider authProvider);

    User updateUserFromOAuth2(User user, OAuth2UserInfo oAuth2UserInfo);

    UserApplicationResponseDto getUserInfoForAdmin(Long userId);

    TokenResponseDto pwSignIn(UserPwSignInRequestDto pwSignInRequestDto);

    UserInfoResponseDto getUserInfo(User user);

    Boolean isAdmin(User user);

    UserAdminListResponseDto getUsers(String email, String name, String phoneNum, String role, Pageable pageable);

    MentorListResponseDto getMentors();

    TokenResponseDto reissueToken(TokenReissueRequestDto tokenReissueRequestDto);

    GetMyApplicationsResponseDto getMyApplications(User user, ApplicationStatus status);

    GetMyApplicationsResponseDto getMyReviewApplications(User user, ApplicationStatus status);

    UserChallengeInfoResponseDto checkUserChallengeInfo(User user);

    void pwSignUp(UserPwSignUpRequestDto pwSignUpRequestDto);

    void updateUser(Long userId, UserUpdateRequestDto userUpdateRequestDto);

    void updateUserForSign(UpdateUserSignInfoRequestDto requestDto);

    void resetPassword(PasswordResetRequestDto passwordResetRequestDto);

    void updatePassword(Long id, PasswordUpdateRequestDto passwordUpdateRequestDto);

    void updateUserForAdmin(Long userId, UpdateUserForAdminRequestDto requestDto);

    void signOut(User user);

    void deleteUser(User user);

    void deleteUserForAdmin(User user, String number);
}
