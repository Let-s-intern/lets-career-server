package org.letscareer.letscareer.global.security.user;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    public UserDetails loadUserByUserId(Long id) throws UsernameNotFoundException {
        return userRepository.findById(id)
                .map(user -> createUser(id, user))
                .orElseThrow(() -> new UsernameNotFoundException(id + " -> DB에서 찾을 수 없음"));
    }

    private PrincipalDetails createUser(Long id, User user) {
        return new PrincipalDetails(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
