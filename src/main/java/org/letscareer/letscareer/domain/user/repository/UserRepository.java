package org.letscareer.letscareer.domain.user.repository;

import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.type.AuthProvider;
import org.letscareer.letscareer.domain.user.vo.UserAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    Optional<User> findByEmailAndAuthProvider(String email, AuthProvider authProvider);
    Optional<User> findByPhoneNum(String phoneNum);
    Optional<User> findByNameAndEmail(String name, String email);
    boolean existsByPhoneNum(String phoneNum);
}
