package org.letscareer.letscareer.domain.review.vo;

import org.letscareer.letscareer.domain.review.type.ReviewQuestionType;

public record CreateReviewItemVo(
        ReviewQuestionType questionType,
        String answer
) {
}
