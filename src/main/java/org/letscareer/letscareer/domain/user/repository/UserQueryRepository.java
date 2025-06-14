package org.letscareer.letscareer.domain.user.repository;

import org.letscareer.letscareer.domain.user.vo.MentorAdminVo;
import org.letscareer.letscareer.domain.user.vo.UserAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserQueryRepository {
    Page<UserAdminVo> findAllUserAdminVos(String email, String name, String phoneNum, String role, Pageable pageable);
    List<MentorAdminVo> findAllMentorAdminVos();
}
