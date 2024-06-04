package org.letscareer.letscareer.domain.user.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.user.dto.request.UserUpdateRequestDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.repository.UserRepository;
import org.letscareer.letscareer.domain.user.vo.UserAdminVo;
import org.letscareer.letscareer.global.error.exception.ConflictException;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.letscareer.letscareer.global.error.exception.InvalidValueException;
import org.letscareer.letscareer.global.security.user.PrincipalDetailsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.letscareer.letscareer.domain.user.error.UserErrorCode.*;
import static org.letscareer.letscareer.global.error.GlobalErrorCode.MISMATCH_PASSWORD;

@Component
@RequiredArgsConstructor
public class UserHelper {
    private final static String REGEX = "^(?=.*[^a-zA-Z0-9]).{8,}$";
    private final UserRepository userRepository;
    private final PrincipalDetailsService principalDetailsService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User findUserByIdOrThrow(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public User findUserByEmailOrThrow(String email) {
        return userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }

    public User findUserByPhoneNumOrNull(String phoneNum) {
        return userRepository.findByPhoneNum(phoneNum).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void validateExistingUser(String phoneNum) {
        if (phoneNum == null)
            return;
        if (userRepository.existsByPhoneNum(phoneNum))
            throw new ConflictException(USER_CONFLICT);
    }

    public void validateRegexPassword(String password) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches())
            throw new InvalidValueException(INVALID_PASSWORD);
    }

    public void validateUpdatedPhoneNumber(User user, UserUpdateRequestDto userUpdateRequestDto) {
        String phoneNum = userUpdateRequestDto.phoneNum();
        System.out.println("phoneNumber::" +  user.getPhoneNum());
        System.out.println("userPhone::" + phoneNum);
        if (Objects.isNull(phoneNum))
            return;
        if (user.getPhoneNum().equals(phoneNum))
            return;
        if (userRepository.existsByPhoneNum(phoneNum))
            throw new ConflictException(USER_CONFLICT);
    }

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public void validatePassword(User user, String inputPassword) {
        if (!matchPassword(inputPassword, user.getPassword())) {
            throw new InvalidValueException(MISMATCH_PASSWORD);
        }
    }

    private boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
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

    public Page<UserAdminVo> findAllUserAdminVos(Pageable pageable) {
        return userRepository.findAllUserAdminVos(pageable);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User findUserByNameAndEmailOrThrow(String name, String email) {
        return userRepository.findByNameAndEmail(name, email)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND));
    }

    public void updatePassword(User user, String randomPassword) {
        String encodedRandomPassword = encodePassword(randomPassword);
        user.updateUserPassword(encodedRandomPassword);
    }
}