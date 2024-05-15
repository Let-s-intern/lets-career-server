package org.letscareer.letscareer.domain.program.repository;

import org.letscareer.letscareer.domain.program.vo.AdminProgramVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProgramQueryRepository {
    Page<AdminProgramVo> findAdminProgramVos(Pageable pageable);
}
