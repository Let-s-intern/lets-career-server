package org.letscareer.letscareer.domain.program.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.curation.vo.CurationItemVo;
import org.letscareer.letscareer.domain.program.entity.SearchCondition;
import org.letscareer.letscareer.domain.program.repository.ProgramRepository;
import org.letscareer.letscareer.domain.program.vo.ProgramForAdminVo;
import org.letscareer.letscareer.domain.program.vo.ProgramForConditionVo;
import org.letscareer.letscareer.domain.program.vo.ProgramReviewNotificationVo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProgramHelper {
    private final ProgramRepository programRepository;

    public Page<ProgramForConditionVo> findProgramForConditionVos(SearchCondition condition) {
        return programRepository.findProgramForConditionVos(condition);
    }

    public Page<ProgramForAdminVo> findProgramForAdminVos(SearchCondition condition) {
        return programRepository.findProgramForAdminVos(condition);
    }

    public List<ProgramReviewNotificationVo> findProgramReviewNotificationVos() {
        return programRepository.findProgramReviewNotificationVos();
    }

    public List<CurationItemVo> findCurationImminentProgramVos() {
        return programRepository.findCurationImminentProgramVos();
    }
}
