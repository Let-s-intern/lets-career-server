package org.letscareer.letscareer.domain.faq.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.faq.type.FaqProgramType;

@Builder
public record FaqDetailVo(
        Long id,
        String question,
        String answer,
        FaqProgramType faqProgramType
) {
}
