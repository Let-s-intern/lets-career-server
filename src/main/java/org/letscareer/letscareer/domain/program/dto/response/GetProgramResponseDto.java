package org.letscareer.letscareer.domain.program.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.program.vo.AdminProgramVo;

import java.util.List;

@Builder(access = AccessLevel.PACKAGE)
public record GetProgramResponseDto(
        AdminProgramVo programInfo,
        List<ProgramClassification> classificationList
) {
}
