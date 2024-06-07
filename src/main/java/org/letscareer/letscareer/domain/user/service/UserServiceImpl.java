package org.letscareer.letscareer.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.letscareer.letscareer.domain.user.dto.request.*;
import org.letscareer.letscareer.domain.user.dto.response.TokenResponseDto;
import org.letscareer.letscareer.domain.user.dto.response.UserAdminListResponseDto;
import org.letscareer.letscareer.domain.user.dto.response.UserInfoResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.helper.UserHelper;
import org.letscareer.letscareer.domain.user.mapper.UserMapper;
import org.letscareer.letscareer.domain.user.type.AuthProvider;
import org.letscareer.letscareer.domain.user.type.UserRole;
import org.letscareer.letscareer.domain.user.vo.UserAdminVo;
import org.letscareer.letscareer.global.common.utils.EmailUtils;
import org.letscareer.letscareer.global.security.jwt.TokenProvider;
import org.letscareer.letscareer.global.security.oauth2.userinfo.OAuth2UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserHelper userHelper;
    private final UserMapper userMapper;
    private final TokenProvider tokenProvider;
    private final EmailUtils emailUtils;

    public User createUserFromOAuth2(OAuth2UserInfo oAuth2UserInfo, AuthProvider authProvider) {
        User newUser = userMapper.toEntityFromOAuth2(oAuth2UserInfo, authProvider);
        return userHelper.saveUser(newUser);
    }

    public User updateUserFromOAuth2(User user, OAuth2UserInfo oAuth2UserInfo) {
        return user.updateUserFromOAuth2(oAuth2UserInfo);
    }

    public void pwSignUp(UserPwSignUpRequestDto pwSignUpRequestDto) {
        userHelper.validateExistingUser(pwSignUpRequestDto.phoneNum());
        userHelper.validateRegexEmail(pwSignUpRequestDto.email());
        userHelper.validateRegexPhoneNumber(pwSignUpRequestDto.phoneNum());
        userHelper.validateRegexPassword(pwSignUpRequestDto.password());
        String encodedPassword = userHelper.encodePassword(pwSignUpRequestDto.password());
        User newUser = userMapper.toEntity(pwSignUpRequestDto, encodedPassword);
        userHelper.saveUser(newUser);
    }

    public TokenResponseDto pwSignIn(UserPwSignInRequestDto pwSignInRequestDto) {
        final User user = userHelper.findUserByEmailOrThrow(pwSignInRequestDto.email());
        userHelper.validatePassword(user, pwSignInRequestDto.password());
        final Authentication authentication = userHelper.userAuthorizationInput(user);
        final String accessToken = tokenProvider.createAccessToken(user.getId(), authentication);
        final String refreshToken = tokenProvider.createRefreshToken(user.getId(), authentication);
        return userMapper.toTokenResponseDto(accessToken, refreshToken);
    }

    public void updateUser(User user, UserUpdateRequestDto userUpdateRequestDto) {
        userHelper.validateRegexEmail(userUpdateRequestDto.email());
        userHelper.validateRegexPhoneNumber(userUpdateRequestDto.phoneNum());
        userHelper.validateUpdatedPhoneNumber(user, userUpdateRequestDto);
        userHelper.updateUser(user, userUpdateRequestDto);
    }

    public UserAdminListResponseDto getUsers(Pageable pageable) {
        Page<UserAdminVo> userAdminList = userHelper.findAllUserAdminVos(pageable);
        return userMapper.toUserAdminListResponseDto(userAdminList);
    }

    @Override
    public void resetPassword(PasswordResetRequestDto passwordResetRequestDto) {
        User user = userHelper.findUserByNameAndEmailOrThrow(passwordResetRequestDto.name(), passwordResetRequestDto.email());
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
    public TokenResponseDto reissueToken(TokenReissueRequestDto tokenReissueRequestDto) {
        String newAccessToken = tokenProvider.reissueAccessToken(tokenReissueRequestDto);
        return TokenResponseDto.of(newAccessToken, tokenReissueRequestDto.refreshToken());
    }

    public UserInfoResponseDto getUserInfo(User user) {
        return userMapper.toUserInfoResponseDto(user);
    }

    public void deleteUser(User user) {
        tokenProvider.deleteRefreshToken(user.getId());
        userHelper.deleteUser(user);
    }

    public Boolean isAdmin(User user) {
        return user.getRole().equals(UserRole.ADMIN);
    }

}