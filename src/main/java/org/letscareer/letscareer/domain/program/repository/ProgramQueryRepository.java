package org.letscareer.letscareer.domain.program.repository;

import org.letscareer.letscareer.domain.curation.vo.CurationItemVo;
import org.letscareer.letscareer.domain.program.entity.SearchCondition;
import org.letscareer.letscareer.domain.program.vo.ProgramForAdminVo;
import org.letscareer.letscareer.domain.program.vo.ProgramForConditionVo;
import org.letscareer.letscareer.domain.program.vo.ProgramReviewNotificationVo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProgramQueryRepository {

    Page<ProgramForConditionVo> findProgramForConditionVos(SearchCondition condition);

    Page<ProgramForAdminVo> findProgramForAdminVos(SearchCondition condition);

    List<ProgramReviewNotificationVo> findProgramReviewNotificationVos();

    List<CurationItemVo> findCurationImminentProgramVos();
}
