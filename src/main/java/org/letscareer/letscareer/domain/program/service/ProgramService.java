package org.letscareer.letscareer.domain.program.service;

import org.letscareer.letscareer.domain.program.dto.response.GetProgramsResponseDto;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public interface ProgramService {
    GetProgramsResponseDto getPrograms(Pageable pageable);
}
