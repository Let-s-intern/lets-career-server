package org.letscareer.letscareer.domain.classification.dto.request;

import org.letscareer.letscareer.domain.classification.type.ProgramClassification;

public record CreateClassificationRequestDto(
        ProgramClassification programClassification
) {
}
