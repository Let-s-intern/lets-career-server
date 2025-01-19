package org.letscareer.letscareer.domain.review.dto.request;

import jakarta.validation.constraints.NotNull;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.review.vo.CreateReviewItemVo;

import java.util.List;

public record CreateReviewRequestDto(
        @NotNull
        ProgramType programType,
        @NotNull
        Integer score,
        @NotNull
        Integer npsScore,
        @NotNull
        String goodPoint,
        @NotNull
        String badPoint,
        List<CreateReviewItemVo> reviewItemList
) {
}
