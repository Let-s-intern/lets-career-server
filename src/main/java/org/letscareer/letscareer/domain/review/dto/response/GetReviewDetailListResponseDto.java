package org.letscareer.letscareer.domain.review.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.review.vo.ReviewAdminVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetReviewDetailListResponseDto(
        List<ReviewAdminVo> reviewList
) {
    public static GetReviewDetailListResponseDto of(List<ReviewAdminVo> reviewList) {
        return GetReviewDetailListResponseDto.builder()
                .reviewList(reviewList)
                .build();
    }
}
