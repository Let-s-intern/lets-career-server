package org.letscareer.letscareer.domain.review.vo;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record ReviewVo<T>(
        T reviewInfo,
        List<ReviewItemAdminVo> reviewItemList
) {
    public static <T> ReviewVo of(T reviewInfo, List<ReviewItemAdminVo> reviewItemList) {
        return ReviewVo.builder()
                .reviewInfo(reviewInfo)
                .reviewItemList(reviewItemList)
                .build();
    }
}
