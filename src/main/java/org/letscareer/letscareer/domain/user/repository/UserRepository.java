package org.letscareer.letscareer.domain.user.repository;

import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.type.AuthProvider;
import org.letscareer.letscareer.domain.user.vo.UserAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserQueryRepository {
    Optional<User> findFirstByEmailAndAuthProviderOrderByIdDesc(String email, AuthProvider authProvider);
    Optional<User> findFirstByEmailAndNameAndPhoneNumOrderByIdDesc(String email, String name, String phoneNum);
    Optional<User> findFirstByPhoneNum(String phoneNum);
    Optional<User> findByNameAndEmail(String name, String email);
    boolean existsByPhoneNum(String phoneNum);
    boolean existsByPhoneNumAndAuthProvider(String phoneNum, AuthProvider authProvider);
    boolean existsByEmailAndAuthProvider(String email, AuthProvider authProvider);


}
