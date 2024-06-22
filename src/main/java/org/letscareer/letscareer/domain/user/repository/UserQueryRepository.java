package org.letscareer.letscareer.domain.user.repository;

import org.letscareer.letscareer.domain.user.vo.UserAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserQueryRepository {
    Page<UserAdminVo> findAllUserAdminVos(String email, String name, String phoneNum, Pageable pageable);
}
