package org.letscareer.letscareer.domain.review.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.review.vo.ReviewMyVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetMyReviewResponseDto(
        ReviewMyVo reviewInfo
) {
    public static GetMyReviewResponseDto of(ReviewMyVo reviewInfo) {
        return GetMyReviewResponseDto.builder()
                .reviewInfo(reviewInfo)
                .build();
    }
}
