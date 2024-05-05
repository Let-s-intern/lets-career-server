package org.letscareer.letscareer.domain.user.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.user.domain.User;
import org.letscareer.letscareer.domain.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserHelper {
    private final UserRepository userRepository;

    public User findUserByEmailOrNull(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User saveUserAndReturn(User user) {
        return userRepository.save(user);
    }
}
