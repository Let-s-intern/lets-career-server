package org.letscareer.letscareer.domain.program.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.program.entity.SearchCondition;
import org.letscareer.letscareer.domain.program.repository.ProgramRepository;
import org.letscareer.letscareer.domain.program.vo.ProgramForAdminVo;
import org.letscareer.letscareer.domain.program.vo.ProgramForConditionVo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

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
}
