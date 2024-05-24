package org.letscareer.letscareer.domain.review.dto.request;

public record CreateReviewRequestDto(
        Long programId,
        String npsAns,
        Boolean npsCheckAns,
        Integer nps,
        String content,
        Integer score
) {
}
