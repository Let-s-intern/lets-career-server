package org.letscareer.letscareer.domain.review.dto.request;

public record CreateReviewRequestDto(
        Long programId,
        String npsAns,
        Integer nps,
        String content,
        Integer score
) {
}
