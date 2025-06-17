package org.letscareer.letscareer.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.letscareer.letscareer.domain.application.dto.response.GetMyApplicationsResponseDto;
import org.letscareer.letscareer.domain.application.helper.ApplicationHelper;
import org.letscareer.letscareer.domain.application.mapper.ApplicationMapper;
import org.letscareer.letscareer.domain.application.type.ApplicationStatus;
import org.letscareer.letscareer.domain.application.vo.MyApplicationVo;
import org.letscareer.letscareer.domain.nhn.dto.request.SignUpParameter;
import org.letscareer.letscareer.domain.nhn.provider.NhnProvider;
import org.letscareer.letscareer.domain.user.dto.request.*;
import org.letscareer.letscareer.domain.user.dto.response.*;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.helper.UserHelper;
import org.letscareer.letscareer.domain.user.mapper.UserMapper;
import org.letscareer.letscareer.domain.user.type.AuthProvider;
import org.letscareer.letscareer.domain.user.type.UserRole;
import org.letscareer.letscareer.domain.user.vo.MentorAdminVo;
import org.letscareer.letscareer.domain.user.vo.UserAdminVo;
import org.letscareer.letscareer.domain.withdraw.helper.WithdrawHelper;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.letscareer.letscareer.global.common.utils.email.EmailUtils;
import org.letscareer.letscareer.global.common.utils.encoder.EncoderUtil;
import org.letscareer.letscareer.global.security.jwt.TokenProvider;
import org.letscareer.letscareer.global.security.oauth2.userinfo.OAuth2UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    private final EncoderUtil encoderUtil;
    private final NhnProvider nhnProvider;

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
    public UserApplicationResponseDto getUserInfoForAdmin(Long userId) {
        User user = userHelper.findUserByIdOrThrow(userId);
        UserInfoResponseDto userInfo = userMapper.toUserInfoResponseDto(user, String.valueOf(user.getId()));
        List<UserApplicationInfo> applicationInfo = applicationHelper.findUserApplicationInfo(user.getId());
        return userMapper.toUserApplicationResponseDto(userInfo, applicationInfo);
    }

    @Override
    public void pwSignUp(UserPwSignUpRequestDto pwSignUpRequestDto) {
        userHelper.validateExistingUser(pwSignUpRequestDto);
        userHelper.validateRegexEmail(pwSignUpRequestDto.email());
        userHelper.validateRegexPhoneNumber(pwSignUpRequestDto.phoneNum());
        userHelper.validateRegexPassword(pwSignUpRequestDto.password());
        String encodedPassword = encoderUtil.encodePassword(pwSignUpRequestDto.password());
        User newUser = userMapper.toEntity(pwSignUpRequestDto, encodedPassword);
        userHelper.saveUser(newUser);
        sendSignUpKakaoMessage(newUser);
    }

    @Override
    public TokenResponseDto pwSignIn(UserPwSignInRequestDto pwSignInRequestDto) {
        final User user = userHelper.findUserByEmailAndAuthProviderOrThrow(pwSignInRequestDto.email(), AuthProvider.SERVICE);
        encoderUtil.validatePassword(user, pwSignInRequestDto.password());
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
        sendSocialSignUpKakaoMessage(user);
    }

    @Override
    public void updateUserForSign(UpdateUserSignInfoRequestDto requestDto) {
        User user = userHelper.findUserByEmailAndAuthProviderOrThrow(requestDto.email(), AuthProvider.SERVICE);
        user.updateUserAdditionInfo(requestDto);
    }

    @Override
    public UserAdminListResponseDto getUsers(String email, String name, String phoneNum, String role, Pageable pageable) {
        Page<UserAdminVo> userAdminList = userHelper.findAllUserAdminVos(email, name, phoneNum, role, pageable);
        List<UserAdminListInfo> userAdminListInfo = createUserAdminListInfo(userAdminList.getContent());
        PageInfo pageInfo = PageInfo.of(userAdminList);
        return userMapper.toUserAdminListResponseDto(userAdminListInfo, pageInfo);
    }

    @Override
    public MentorListResponseDto getMentors() {
        List<MentorAdminVo> mentorAdminVos = userHelper.findAllMentorAdminVos();
        return userMapper.toMentorListResponseDto(mentorAdminVos);
    }

    @Override
    public void resetPassword(PasswordResetRequestDto passwordResetRequestDto) {
        User user = userHelper.findUserByEmailAndNameAndPhoneNumOrThrow(passwordResetRequestDto);
        userHelper.validateAuthProvider(user.getAuthProvider());
        String randomPassword = RandomStringUtils.randomAlphanumeric(8);
        String encodedRandomPassword = encoderUtil.encodePassword(randomPassword);
        user.updateUserPassword(encodedRandomPassword);
        emailUtils.sendPasswordResetEmail(user.getEmail(), randomPassword);
    }

    @Override
    public void updatePassword(Long userId, PasswordUpdateRequestDto passwordUpdateRequestDto) {
        User user = userHelper.findUserByIdOrThrow(userId);
        userHelper.validateRegexPassword(passwordUpdateRequestDto.newPassword());
        encoderUtil.validatePassword(user, passwordUpdateRequestDto.password());
        String encodedRandomPassword = encoderUtil.encodePassword(passwordUpdateRequestDto.newPassword());
        user.updateUserPassword(encodedRandomPassword);
    }

    @Override
    public void updateUserForAdmin(Long userId, UpdateUserForAdminRequestDto requestDto) {
        User user = userHelper.findUserByIdOrThrow(userId);
        user.updateUserForAdmin(requestDto);
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
    public GetMyApplicationsResponseDto getMyReviewApplications(User user, ApplicationStatus status) {
        List<MyApplicationVo> applicationList = applicationHelper.getMyReviewApplications(user.getId(), status);
        return applicationMapper.toGetMyApplicationsResponseDto(applicationList);
    }

    @Override
    public UserInfoResponseDto getUserInfo(User user) {
        String stringId = encoderUtil.encodeUserData(user);
        return userMapper.toUserInfoResponseDto(user, stringId);
    }

    @Override
    public void deleteUser(User user) {
        tokenProvider.deleteRefreshToken(user.getId());
        withdrawHelper.createUserWithdrawalRecordAndSave(user);
        userHelper.deleteUser(user);
    }

    @Override
    public void deleteUserForAdmin(User admin, String number) {
        userHelper.validateAdminUser(admin);
        User user = userHelper.findUserByPhoneNumOrThrow(number);
        tokenProvider.deleteRefreshToken(user.getId());
        userHelper.deleteUser(user);
    }

    @Override
    public Boolean isAdmin(User user) {
        return user.getRole().equals(UserRole.ADMIN);
    }

    @Override
    public Boolean isMentor(User user) {
        return user.getIsMentor().equals(Boolean.TRUE);
    }

    private void sendSignUpKakaoMessage(User newUser) {
        SignUpParameter requestParameter = SignUpParameter.of(newUser);
        nhnProvider.sendKakaoMessage(newUser, requestParameter, "sign_up_confirm");
    }

    private void sendSocialSignUpKakaoMessage(User newUser) {
        if (AuthProvider.SERVICE.equals(newUser.getAuthProvider())) return;
        if (!Objects.isNull(newUser.getContactEmail())) return;
        SignUpParameter requestParameter = SignUpParameter.of(newUser);
        nhnProvider.sendKakaoMessage(newUser, requestParameter, "sign_up_confirm");
    }

    private List<UserAdminListInfo> createUserAdminListInfo(List<UserAdminVo> userAdminList) {
        return userAdminList.stream()
                .map(userAdminVo -> userMapper.toUserAdminListInfo(
                        userAdminVo,
                        applicationHelper.findUserApplicationInfo(userAdminVo.id())
                ))
                .collect(Collectors.toList());
    }
}