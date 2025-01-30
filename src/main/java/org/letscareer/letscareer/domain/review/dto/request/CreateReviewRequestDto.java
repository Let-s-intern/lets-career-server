package org.letscareer.letscareer.domain.review.dto.request;

import jakarta.validation.constraints.NotNull;
import org.letscareer.letscareer.domain.review.type.ReviewProgramType;
import org.letscareer.letscareer.domain.review.vo.CreateReviewItemVo;

import java.util.List;

public record CreateReviewRequestDto(
        @NotNull
        ReviewProgramType type,
        @NotNull
        Integer score,
        @NotNull
        Integer npsScore,
        @NotNull
        List<CreateReviewItemVo> reviewItemList
) {
}
