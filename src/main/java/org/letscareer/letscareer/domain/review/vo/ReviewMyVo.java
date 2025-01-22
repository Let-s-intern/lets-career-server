package org.letscareer.letscareer.domain.review.vo;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record ReviewMyVo<T>(
        T reviewInfo,
        List<ReviewItemVo> reviewItemList
) {
    public static <T> ReviewMyVo of(T reviewInfo, List<ReviewItemVo> reviewItemList) {
        return ReviewMyVo.builder()
                .reviewInfo(reviewInfo)
                .reviewItemList(reviewItemList)
                .build();
    }
}
