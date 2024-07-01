package org.letscareer.letscareer.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.letscareer.letscareer.domain.application.dto.response.GetMyApplicationsResponseDto;
import org.letscareer.letscareer.domain.application.helper.ApplicationHelper;
import org.letscareer.letscareer.domain.application.mapper.ApplicationMapper;
import org.letscareer.letscareer.domain.application.type.ApplicationStatus;
import org.letscareer.letscareer.domain.application.vo.MyApplicationVo;
import org.letscareer.letscareer.domain.user.dto.request.*;
import org.letscareer.letscareer.domain.user.dto.response.TokenResponseDto;
import org.letscareer.letscareer.domain.user.dto.response.UserAdminListResponseDto;
import org.letscareer.letscareer.domain.user.dto.response.UserChallengeInfoResponseDto;
import org.letscareer.letscareer.domain.user.dto.response.UserInfoResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.helper.UserHelper;
import org.letscareer.letscareer.domain.user.mapper.UserMapper;
import org.letscareer.letscareer.domain.user.type.AuthProvider;
import org.letscareer.letscareer.domain.user.type.UserRole;
import org.letscareer.letscareer.domain.user.vo.UserAdminVo;
import org.letscareer.letscareer.domain.withdraw.helper.WithdrawHelper;
import org.letscareer.letscareer.global.common.utils.EmailUtils;
import org.letscareer.letscareer.global.security.jwt.TokenProvider;
import org.letscareer.letscareer.global.security.oauth2.userinfo.OAuth2UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService {
    private final UserHelper userHelper;
    private final UserMapper userMapper;
    private final ApplicationHelper applicationHelper;
    private final ApplicationMapper applicationMapper;
    private final WithdrawHelper withdrawHelper;
    private final TokenProvider tokenProvider;
    private final EmailUtils emailUtils;

    @Override
    public User createUserFromOAuth2(OAuth2UserInfo oAuth2UserInfo, AuthProvider authProvider) {
        User newUser = userMapper.toEntityFromOAuth2(oAuth2UserInfo, authProvider);
        return userHelper.saveUser(newUser);
    }

    @Override
    public User updateUserFromOAuth2(User user, OAuth2UserInfo oAuth2UserInfo) {
        return user.updateUserFromOAuth2(oAuth2UserInfo);
    }

    @Override
    public void pwSignUp(UserPwSignUpRequestDto pwSignUpRequestDto) {
        userHelper.validateExistingUser(pwSignUpRequestDto);
        userHelper.validateRegexEmail(pwSignUpRequestDto.email());
        userHelper.validateRegexPhoneNumber(pwSignUpRequestDto.phoneNum());
        userHelper.validateRegexPassword(pwSignUpRequestDto.password());
        String encodedPassword = userHelper.encodePassword(pwSignUpRequestDto.password());
        User newUser = userMapper.toEntity(pwSignUpRequestDto, encodedPassword);
        userHelper.saveUser(newUser);
    }

    @Override
    public TokenResponseDto pwSignIn(UserPwSignInRequestDto pwSignInRequestDto) {
        final User user = userHelper.findUserByEmailAndAuthProviderOrThrow(pwSignInRequestDto.email(), AuthProvider.SERVICE);
        userHelper.validatePassword(user, pwSignInRequestDto.password());
        final Authentication authentication = userHelper.userAuthorizationInput(user);
        final String accessToken = tokenProvider.createAccessToken(user.getId(), authentication);
        final String refreshToken = tokenProvider.createRefreshToken(user.getId(), authentication);
        return userMapper.toTokenResponseDto(accessToken, refreshToken);
    }

    @Override
    public void updateUser(Long userId, UserUpdateRequestDto userUpdateRequestDto) {
        User user = userHelper.findUserByIdOrThrow(userId);
        userHelper.validateRegexEmail(userUpdateRequestDto.email());
        userHelper.validateRegexEmail(userUpdateRequestDto.contactEmail());
        userHelper.validateRegexPhoneNumber(userUpdateRequestDto.phoneNum());
        userHelper.validateUpdatedPhoneNumber(user, userUpdateRequestDto);
        userHelper.updateUser(user, userUpdateRequestDto);
    }

    @Override
    public void updateUserForSign(UpdateUserSignInfoRequestDto requestDto) {
        User user = userHelper.findUserByEmailAndAuthProviderOrThrow(requestDto.email(), AuthProvider.SERVICE);
        user.updateUserAdditionInfo(requestDto);
    }

    @Override
    public UserAdminListResponseDto getUsers(String email, String name, String phoneNum, Pageable pageable) {
        Page<UserAdminVo> userAdminList = userHelper.findAllUserAdminVos(email, name, phoneNum, pageable);
        return userMapper.toUserAdminListResponseDto(userAdminList);
    }

    @Override
    public void resetPassword(PasswordResetRequestDto passwordResetRequestDto) {
        User user = userHelper.findUserByEmailAndNameAndPhoneNumAndAuthProviderOrThrow(passwordResetRequestDto, AuthProvider.SERVICE);
        String randomPassword = RandomStringUtils.randomAlphanumeric(8);
        userHelper.updatePassword(user, randomPassword);
        emailUtils.sendPasswordResetEmail(user.getEmail(), randomPassword);
    }

    @Override
    public void updatePassword(Long userId, PasswordUpdateRequestDto passwordUpdateRequestDto) {
        User user = userHelper.findUserByIdOrThrow(userId);
        userHelper.validateRegexPassword(passwordUpdateRequestDto.newPassword());
        userHelper.validatePassword(user, passwordUpdateRequestDto.password());
        userHelper.updatePassword(user, passwordUpdateRequestDto.newPassword());
    }

    @Override
    public void signOut(User user) {
        tokenProvider.deleteRefreshToken(user.getId());
    }

    @Override
    public UserChallengeInfoResponseDto checkUserChallengeInfo(User user) {
        Boolean pass = userHelper.checkUserChallengeInfo(user);
        return userMapper.toUserChallengeInfoResponseDto(pass);
    }

    @Override
    public TokenResponseDto reissueToken(TokenReissueRequestDto tokenReissueRequestDto) {
        String newAccessToken = tokenProvider.reissueAccessToken(tokenReissueRequestDto);
        return TokenResponseDto.of(newAccessToken, tokenReissueRequestDto.refreshToken());
    }

    @Override
    public GetMyApplicationsResponseDto getMyApplications(User user, ApplicationStatus status) {
        List<MyApplicationVo> applicationList = applicationHelper.getMyApplications(user.getId(), status);
        return applicationMapper.toGetMyApplicationsResponseDto(applicationList);
    }

    @Override
    public UserInfoResponseDto getUserInfo(User user) {
        return userMapper.toUserInfoResponseDto(user);
    }

    @Override
    public void deleteUser(User user) {
        tokenProvider.deleteRefreshToken(user.getId());
        withdrawHelper.createUserWithdrawalRecordAndSave(user);
        userHelper.deleteUser(user);
    }

    @Override
    public Boolean isAdmin(User user) {
        return user.getRole().equals(UserRole.ADMIN);
    }

}