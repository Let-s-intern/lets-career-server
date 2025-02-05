package org.letscareer.letscareer.domain.review.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.review.vo.old.OldReviewAdminVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetOldReviewDetailListResponseDto(
        List<OldReviewAdminVo> reviewList
) {
    public static GetOldReviewDetailListResponseDto of(List<OldReviewAdminVo> reviewList) {
        return GetOldReviewDetailListResponseDto.builder()
                .reviewList(reviewList)
                .build();
    }
}
