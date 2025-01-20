package org.letscareer.letscareer.domain.review.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetReviewResponseDto(
        List<ReviewVo> reviewList
) {
    public static GetReviewResponseDto of(List<ReviewVo> reviewList) {
        return GetReviewResponseDto.builder()
                .reviewList(reviewList)
                .build();
    }
}
