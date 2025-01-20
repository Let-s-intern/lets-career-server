package org.letscareer.letscareer.domain.review.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetReviewResponseDto(
        List<ReviewVo> reviewList,
        PageInfo pageInfo
) {
    public static GetReviewResponseDto of(List<ReviewVo> reviewList, PageInfo pageInfo) {
        return GetReviewResponseDto.builder()
                .reviewList(reviewList)
                .pageInfo(pageInfo)
                .build();
    }
}
