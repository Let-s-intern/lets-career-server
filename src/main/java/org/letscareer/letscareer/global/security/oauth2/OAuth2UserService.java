package org.letscareer.letscareer.global.security.oauth2;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.user.domain.User;
import org.letscareer.letscareer.domain.user.helper.UserHelper;
import org.letscareer.letscareer.domain.user.service.UserService;
import org.letscareer.letscareer.global.security.oauth2.userinfo.OAuth2UserInfo;
import org.letscareer.letscareer.global.security.user.PrincipalDetails;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
@RequiredArgsConstructor
public class OAuth2UserService implements org.springframework.security.oauth2.client.userinfo.OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserHelper userHelper;
    private final UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        org.springframework.security.oauth2.client.userinfo.OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(oAuth2UserRequest);
        return processOAuth2User(oAuth2UserRequest, oAuth2User);
    }

    protected OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        AuthProvider authProvider = AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase());
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(authProvider, oAuth2User.getAttributes());
        if(!StringUtils.hasText(oAuth2UserInfo.getEmail()))
            throw new RuntimeException("Email Not Found From OAuth2 Provider");

        return validateEmailAndCreateUserIfNeed(oAuth2UserInfo, authProvider);
    }

    private PrincipalDetails validateEmailAndCreateUserIfNeed(OAuth2UserInfo oAuth2UserInfo, AuthProvider authProvider) {
        User user = userHelper.findUserByEmailOrNull(oAuth2UserInfo.getEmail());
        PrincipalDetails principalDetails = null;

        /* 신규 가입 */
        if(!existingUser(user)) {
            user = userService.createUserFromOAuth2(oAuth2UserInfo, authProvider);
            principalDetails = new PrincipalDetails(user);
        }

        /* 동일 AuthProvider 에서 가입 이력 존재 */
        else if(equalAuthProvider(user, authProvider)) {
            user = userService.updateUserFromOAuth2(user, oAuth2UserInfo);
            principalDetails = new PrincipalDetails(user);
        }

        /* 다른 AuthProvider 에서 가입 이력 존재 */
        else if(!equalAuthProvider(user, authProvider)) {
            principalDetails = new PrincipalDetails(user);
            principalDetails.setDuplicateEmail(true);
        }

        return principalDetails;
    }

    private boolean existingUser(User user) {
        return user != null;
    }

    private boolean equalAuthProvider(User user, AuthProvider authProvider) {
        return user.getAuthProvider().equals(authProvider);
    }
}
