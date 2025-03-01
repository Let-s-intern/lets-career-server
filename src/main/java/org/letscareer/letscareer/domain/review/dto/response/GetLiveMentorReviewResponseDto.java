package org.letscareer.letscareer.domain.review.dto.response;

import org.letscareer.letscareer.domain.review.type.ReviewQuestionType;

public record GetLiveMentorReviewResponseDto(
        ReviewQuestionType questionType,
        String answer
) {
}
