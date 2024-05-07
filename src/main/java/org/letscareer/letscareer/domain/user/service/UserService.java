package org.letscareer.letscareer.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.user.dto.request.UserAddInfoRequestDto;
import org.letscareer.letscareer.domain.user.dto.request.UserPwSignInRequestDto;
import org.letscareer.letscareer.domain.user.dto.request.UserPwSignUpRequestDto;
import org.letscareer.letscareer.domain.user.dto.response.TokenResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.helper.UserHelper;
import org.letscareer.letscareer.domain.user.mapper.UserMapper;
import org.letscareer.letscareer.domain.user.type.AuthProvider;
import org.letscareer.letscareer.global.security.jwt.TokenProvider;
import org.letscareer.letscareer.global.security.oauth2.userinfo.OAuth2UserInfo;
import org.letscareer.letscareer.global.security.user.PrincipalDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserHelper userHelper;
    private final UserMapper userMapper;
    private final TokenProvider tokenProvider;

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

    public TokenResponseDto pwSignIn(UserPwSignInRequestDto pwSignInRequestDto) {
        final User user = userHelper.findUserByEmailOrThrow(pwSignInRequestDto.email());
        userHelper.validatePassword(user, pwSignInRequestDto.password());
        final Authentication authentication = userHelper.userAuthorizationInput(user);
        final String accessToken = tokenProvider.createAccessToken(user.getId(), authentication);
        final String refreshToken = tokenProvider.createRefreshToken(user.getId(), authentication);
        return userMapper.toTokenResponseDto(accessToken, refreshToken);
    }

    public void addUserInfo(PrincipalDetails principalDetails, UserAddInfoRequestDto addInfoRequestDto) {
        User user = userHelper.findUserByIdOrThrow(principalDetails.getId());
        userHelper.addUserInfo(user, addInfoRequestDto);
    }
}