package org.letscareer.letscareer.domain.user.mapper;

import org.letscareer.letscareer.domain.user.dto.request.UserPwSignUpRequestDto;
import org.letscareer.letscareer.domain.user.dto.response.*;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.type.AuthProvider;
import org.letscareer.letscareer.domain.user.vo.UserAdminVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.letscareer.letscareer.global.security.oauth2.userinfo.OAuth2UserInfo;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public UserAdminListResponseDto toUserAdminListResponseDto(List<UserAdminListInfo> userAdminListInfo,
                                                               PageInfo pageInfo) {
        return UserAdminListResponseDto.of(userAdminListInfo, pageInfo);
    }

    public UserAdminListInfo toUserAdminListInfo(UserAdminVo userInfo,
                                                 List<UserApplicationInfo> applicationInfos) {
        return UserAdminListInfo.of(userInfo, applicationInfos);
    }

    public UserInfoResponseDto toUserInfoResponseDto(User user, String stringId) {
        return UserInfoResponseDto.of(user, stringId);
    }

    public UserChallengeInfoResponseDto toUserChallengeInfoResponseDto(Boolean pass) {
        return UserChallengeInfoResponseDto.of(pass);
    }
}
