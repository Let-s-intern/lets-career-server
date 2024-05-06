package org.letscareer.letscareer.domain.user.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.repository.UserRepository;
import org.letscareer.letscareer.global.error.GlobalErrorCode;
import org.letscareer.letscareer.global.error.exception.ConflictException;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserHelper {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User findUserByEmailOrNull(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void validateExistingUser(String phoneNum) {
        User user = userRepository.findByPhoneNum(phoneNum).orElse(null);
        if(user != null) throw new ConflictException();
    }

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }


}
