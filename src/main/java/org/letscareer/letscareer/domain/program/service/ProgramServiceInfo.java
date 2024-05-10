package org.letscareer.letscareer.domain.program.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.program.dto.response.GetProgramsResponseDto;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@RequiredArgsConstructor
@Service
public class ProgramServiceInfo implements ProgramService {

    @Override
    public GetProgramsResponseDto getPrograms(Pageable pageable) {
        return null;
    }
}
