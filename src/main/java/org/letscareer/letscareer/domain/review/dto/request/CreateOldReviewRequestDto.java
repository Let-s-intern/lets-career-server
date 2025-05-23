package org.letscareer.letscareer.domain.review.dto.request;

import jakarta.validation.constraints.NotNull;

public record CreateOldReviewRequestDto(
        Long programId,
        @NotNull
        String npsAns,
        @NotNull
        Boolean npsCheckAns,
        @NotNull
        Integer nps,
        @NotNull
        String content,
        @NotNull
        Integer score,
        String programDetail
) {
}
