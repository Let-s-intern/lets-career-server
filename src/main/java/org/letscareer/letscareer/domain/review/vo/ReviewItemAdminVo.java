package org.letscareer.letscareer.domain.review.vo;

import org.letscareer.letscareer.domain.review.type.ReviewQuestionType;

public record ReviewItemAdminVo(
        Long reviewItemId,
        ReviewQuestionType questionType,
        String answer,
        Boolean isVisible
) {
}
