package org.letscareer.letscareer.domain.program.service;

import org.letscareer.letscareer.domain.program.dto.response.GetProgramsResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface ProgramService {
    GetProgramsResponseDto getPrograms();
}
