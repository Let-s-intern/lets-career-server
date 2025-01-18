package org.letscareer.letscareer.domain.review.dto.request;

public record UpdateOldReviewRequestDto(
        String npsAns,
        Boolean npsCheckAns,
        Integer nps,
        String content,
        Integer score,
        String programDetail
) {
}
