package org.letscareer.letscareer.domain.user.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.user.dto.request.UserAddInfoRequestDto;
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

import static org.letscareer.letscareer.global.error.GlobalErrorCode.MISMATCH_PASSWORD;

@Component
@RequiredArgsConstructor
public class UserHelper {
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
        User user = userRepository.findByPhoneNum(phoneNum).orElse(null);
        if (user != null) throw new ConflictException();
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

    public void addUserInfo(User user, UserAddInfoRequestDto addInfoRequestDto) {
        user.addUserInfo(addInfoRequestDto);
    }

    public Page<UserAdminVo> findAllUserAdminVos(Pageable pageable) {
        return userRepository.findAllUserAdminVos(pageable);
    }
}