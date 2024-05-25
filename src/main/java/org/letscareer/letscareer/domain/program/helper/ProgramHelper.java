package org.letscareer.letscareer.domain.program.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.program.dto.request.GetProgramsForDurationRequestDto;
import org.letscareer.letscareer.domain.program.repository.ProgramRepository;
import org.letscareer.letscareer.domain.program.vo.AdminProgramVo;
import org.letscareer.letscareer.domain.program.vo.ProgramForDurationVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProgramHelper {
    private final ProgramRepository programRepository;

    public Page<AdminProgramVo> findAdminProgramVos(Pageable pageable) {
        return programRepository.findAdminProgramVos(pageable);
    }

    public List<ProgramForDurationVo> findProgramForDurationVos(GetProgramsForDurationRequestDto requestDto) {
        return programRepository.findProgramForDurationVos(requestDto);
    }
}
