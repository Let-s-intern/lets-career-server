package org.letscareer.letscareer.domain.review.vo;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record ReviewAdminVo<T>(
        T reviewInfo,
        List<ReviewItemAdminVo> reviewItemList
) {
    public static <T> ReviewAdminVo of(T reviewInfo, List<ReviewItemAdminVo> reviewItemList) {
        return ReviewAdminVo.builder()
                .reviewInfo(reviewInfo)
                .reviewItemList(reviewItemList)
                .build();
    }
}
