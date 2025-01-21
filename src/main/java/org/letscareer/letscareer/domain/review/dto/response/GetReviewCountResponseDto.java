package org.letscareer.letscareer.domain.review.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
@Builder(access = AccessLevel.PRIVATE)
public record GetReviewCountResponseDto(
        Long count
) {
    public static GetReviewCountResponseDto of(Long reviewCount, Long blogReviewCount) {
        return GetReviewCountResponseDto.builder()
                .count(reviewCount + blogReviewCount)
                .build();
    }
}
