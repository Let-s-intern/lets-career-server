package org.letscareer.letscareer.domain.user.mapper;

import org.letscareer.letscareer.domain.user.dto.request.UserPwSignUpRequestDto;
import org.letscareer.letscareer.domain.user.dto.response.TokenResponseDto;
import org.letscareer.letscareer.domain.user.dto.response.UserAdminListResponseDto;
import org.letscareer.letscareer.domain.user.dto.response.UserChallengeInfoResponseDto;
import org.letscareer.letscareer.domain.user.dto.response.UserInfoResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.type.AuthProvider;
import org.letscareer.letscareer.domain.user.vo.UserAdminVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.letscareer.letscareer.global.security.oauth2.userinfo.OAuth2UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntityFromOAuth2(OAuth2UserInfo oAuth2UserInfo, AuthProvider authProvider) {
        return User.createUserFromOAuth2(oAuth2UserInfo, authProvider);
    }

    public User toEntity(UserPwSignUpRequestDto pwSignUpRequestDto, String encodedPassword) {
        return User.createUser(pwSignUpRequestDto, encodedPassword);
    }

    public TokenResponseDto toTokenResponseDto(String accessToken, String refreshToken) {
        return TokenResponseDto.of(accessToken, refreshToken);
    }

    public UserAdminListResponseDto toUserAdminListResponseDto(Page<UserAdminVo> userAdminList) {
        PageInfo pageInfo = PageInfo.of(userAdminList);
        return UserAdminListResponseDto.of(userAdminList, pageInfo);
    }

    public UserInfoResponseDto toUserInfoResponseDto(User user) {
        return UserInfoResponseDto.of(user);
    }

    public UserChallengeInfoResponseDto toUserChallengeInfoResponseDto(Boolean pass) {
        return UserChallengeInfoResponseDto.of(pass);
    }
}
