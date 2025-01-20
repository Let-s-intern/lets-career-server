package org.letscareer.letscareer.domain.review.vo;

import org.letscareer.letscareer.domain.review.type.ReviewQuestionType;

public record ReviewItemVo(
        Long reviewItemId,
        ReviewQuestionType questionType,
        String answer
) {
}
