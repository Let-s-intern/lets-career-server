package org.letscareer.letscareer.domain.classification.dto.request;

import org.letscareer.letscareer.domain.classification.type.ProgramType;

public record CreateClassificationRequestDto(
        ProgramType programType
) {
}
