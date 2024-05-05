package org.letscareer.letscareer.domain.user.repository;

import org.letscareer.letscareer.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Long, User> {

    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
