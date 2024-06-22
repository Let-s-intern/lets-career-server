package org.letscareer.letscareer.domain.program.service;

import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.program.dto.response.GetProgramsForAdminResponseDto;
import org.letscareer.letscareer.domain.program.dto.response.GetProgramsForConditionResponseDto;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public interface ProgramService {

    GetProgramsForConditionResponseDto getProgramsForCondition(List<ProgramType> type, List<ProgramClassification> typeList, List<ProgramStatusType> statusList, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    GetProgramsForAdminResponseDto getProgramsForAdmin(List<ProgramType> type, List<ProgramClassification> typeList, List<ProgramStatusType> statusList, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
