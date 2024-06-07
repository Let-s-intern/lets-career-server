package org.letscareer.letscareer.domain.program.repository;

import org.letscareer.letscareer.domain.program.entity.SearchCondition;
import org.letscareer.letscareer.domain.program.vo.ProgramForAdminVo;
import org.letscareer.letscareer.domain.program.vo.ProgramForConditionVo;
import org.springframework.data.domain.Page;

public interface ProgramQueryRepository {

    Page<ProgramForConditionVo> findProgramForConditionVos(SearchCondition condition);

    Page<ProgramForAdminVo> findProgramForAdminVos(SearchCondition condition);
}
