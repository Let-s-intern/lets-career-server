package org.letscareer.letscareer.domain.review.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.review.vo.ReviewAdminVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetReviewForAdminResponseDto(
        List<ReviewAdminVo> reviewList
) {
    public static GetReviewForAdminResponseDto of(List<ReviewAdminVo> reviewList) {
        return GetReviewForAdminResponseDto.builder()
                .reviewList(reviewList)
                .build();
    }
}
