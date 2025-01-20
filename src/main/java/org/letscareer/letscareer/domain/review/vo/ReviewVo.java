package org.letscareer.letscareer.domain.review.vo;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record ReviewVo(
        ReviewInfoVo reviewInfo,
        List<ReviewItemVo> reviewItemList
) {
    public static ReviewVo of(ReviewInfoVo reviewInfo, List<ReviewItemVo> reviewItemList) {
        return ReviewVo.builder()
                .reviewInfo(reviewInfo)
                .reviewItemList(reviewItemList)
                .build();
    }
}
