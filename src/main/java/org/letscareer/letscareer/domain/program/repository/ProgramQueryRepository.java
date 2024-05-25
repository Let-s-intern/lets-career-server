package org.letscareer.letscareer.domain.program.repository;

import org.letscareer.letscareer.domain.program.dto.request.GetProgramsForDurationRequestDto;
import org.letscareer.letscareer.domain.program.vo.AdminProgramVo;
import org.letscareer.letscareer.domain.program.vo.ProgramForDurationVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProgramQueryRepository {
    Page<AdminProgramVo> findAdminProgramVos(Pageable pageable);
    List<ProgramForDurationVo>  findProgramForDurationVos(GetProgramsForDurationRequestDto requestDto);
}
