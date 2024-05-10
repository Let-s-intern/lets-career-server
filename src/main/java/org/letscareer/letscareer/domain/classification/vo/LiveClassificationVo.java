package org.letscareer.letscareer.domain.classification.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;

@Builder
public record LiveClassificationVo(
        ProgramClassification programClassification
) {
}
