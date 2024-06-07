package org.letscareer.letscareer.domain.review.dto.request;

public record UpdateReviewRequestDto(
        String npsAns,
        Boolean npsCheckAns,
        Integer nps,
        String content,
        Integer score
) {
}
