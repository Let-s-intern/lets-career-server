package org.letscareer.letscareer.domain.review.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetReviewForAdminResponseDto<T>(
        T reviewList
) {
    public static <T> GetReviewForAdminResponseDto<?> of(T reviewList) {
        return GetReviewForAdminResponseDto.builder()
                .reviewList(reviewList)
                .build();
    }
}
