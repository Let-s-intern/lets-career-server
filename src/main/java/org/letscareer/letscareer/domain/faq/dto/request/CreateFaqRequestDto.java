package org.letscareer.letscareer.domain.faq.dto.request;

import org.letscareer.letscareer.domain.faq.type.FaqProgramType;

public record CreateFaqRequestDto(
        String question,
        String answer,
        FaqProgramType faqProgramType
) {
}
