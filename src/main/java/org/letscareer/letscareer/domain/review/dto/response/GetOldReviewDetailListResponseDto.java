package org.letscareer.letscareer.domain.review.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.review.vo.old.ReviewAdminVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetOldReviewDetailListResponseDto(
        List<ReviewAdminVo> reviewList
) {
    public static GetOldReviewDetailListResponseDto of(List<ReviewAdminVo> reviewList) {
        return GetOldReviewDetailListResponseDto.builder()
                .reviewList(reviewList)
                .build();
    }
}
