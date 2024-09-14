package org.letscareer.letscareer.domain.user.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.user.dto.request.PasswordResetRequestDto;
import org.letscareer.letscareer.domain.user.dto.request.UserPwSignUpRequestDto;
import org.letscareer.letscareer.domain.user.dto.request.UserUpdateRequestDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.repository.UserRepository;
import org.letscareer.letscareer.domain.user.type.AuthProvider;
import org.letscareer.letscareer.domain.user.type.UserRole;
import org.letscareer.letscareer.domain.user.vo.UserAdminVo;
import org.letscareer.letscareer.global.error.exception.ConflictException;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.letscareer.letscareer.global.error.exception.InvalidValueException;
import org.letscareer.letscareer.global.error.exception.UnauthorizedException;
import org.letscareer.letscareer.global.security.user.PrincipalDetailsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.letscareer.letscareer.domain.user.error.UserErrorCode.*;

@Component
@RequiredArgsConstructor
public class UserHelper {
    private final static String PASSWORD_REGEX = "^(?=.*[^a-zA-Z0-9]).{8,}$";
    private final static String PHONE_NUMBER_REGEX = "010-[0-9]{4}-[0-9]{4}";
    private final static String EMAIL_REGEX = "^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*\\.[A-Za-z]{2,3}$";
    private final UserRepository userRepository;
    private final PrincipalDetailsService principalDetailsService;
//    private final PasswordEncoder passwordEncoder;
//    private final MessageDigestPasswordEncoder hashEncoder;

    public User findUserByIdOrThrow(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public User findUserByEmailAndAuthProviderOrThrow(String email, AuthProvider authProvider) {
        return userRepository.findFirstByEmailAndAuthProviderOrderByIdDesc(email, authProvider)
                .orElseThrow(EntityNotFoundException::new);
    }

    public User findUserByEmailAndNameAndPhoneNumOrThrow(PasswordResetRequestDto requestDto) {
        return userRepository.findFirstByEmailAndNameAndPhoneNumOrderByIdDesc(requestDto.email(), requestDto.name(), requestDto.phoneNum())
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND));
    }

    public User findUserByPhoneNumOrNull(String phoneNum) {
        return userRepository.findFirstByPhoneNum(phoneNum)
                .orElse(null);
    }

    public User findUserByPhoneNumOrThrow(String phoneNum) {
        return userRepository.findFirstByPhoneNum(phoneNum)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void validateExistingUser(UserPwSignUpRequestDto pwSignUpRequestDto) {
        if (userRepository.existsByPhoneNum(pwSignUpRequestDto.phoneNum()))
            throw new ConflictException(USER_PHONE_NUMBER_CONFLICT);
    }

    public void validateAuthProvider(AuthProvider authProvider) {
        switch (authProvider) {
            case KAKAO -> throw new InvalidValueException(INVALID_AUTH_PROVIDER_KAKAO);
            case NAVER -> throw new InvalidValueException(INVALID_AUTH_PROVIDER_NAVER);
        }
    }

    public void validateRegexEmail(String email) {
        if (Objects.isNull(email)) return;
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches())
            throw new InvalidValueException(INVALID_EMAIL);
    }

    public void validateRegexPassword(String password) {
        if (Objects.isNull(password)) return;
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches())
            throw new InvalidValueException(INVALID_PASSWORD);
    }

    public void validateRegexPhoneNumber(String phoneNumber) {
        if (Objects.isNull(phoneNumber)) return;
        Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches())
            throw new InvalidValueException(INVALID_PHONE_NUMBER);
    }

    public void validateUpdatedPhoneNumber(User user, UserUpdateRequestDto userUpdateRequestDto) {
        String phoneNum = userUpdateRequestDto.phoneNum();
        if (Objects.isNull(phoneNum))
            return;
        if (user.getPhoneNum().equals(phoneNum))
            return;
        if (userRepository.existsByPhoneNum(phoneNum))
            throw new ConflictException(USER_PHONE_NUMBER_CONFLICT);
    }

    public void validateAdminUser(User admin) {
        if (!admin.getRole().equals(UserRole.ADMIN))
            throw new UnauthorizedException(IS_NOT_ADMIN);
    }

    public Authentication userAuthorizationInput(User user) {
        UserDetails userDetails = principalDetailsService.loadUserByUserId(user.getId());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    public void updateUser(User user, UserUpdateRequestDto userUpdateRequestDto) {
        user.updateUser(userUpdateRequestDto);
    }

    public void updateContactEmail(User user, String email) {
        user.updateContactEmail(email);
        userRepository.save(user);
    }

    public Page<UserAdminVo> findAllUserAdminVos(String email, String name, String phoneNum, Pageable pageable) {
        return userRepository.findAllUserAdminVos(email, name, phoneNum, pageable);
    }

    public Boolean checkUserChallengeInfo(User user) {
        return checkStringNotNullAndNotEmpty(user.getUniversity())
                && !Objects.isNull(user.getGrade())
                && checkStringNotNullAndNotEmpty(user.getMajor())
                && checkStringNotNullAndNotEmpty(user.getWishJob())
                && checkStringNotNullAndNotEmpty(user.getWishCompany());
    }

    private Boolean checkStringNotNullAndNotEmpty(String s) {
        return !(Objects.isNull(s) || s.isEmpty());
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}