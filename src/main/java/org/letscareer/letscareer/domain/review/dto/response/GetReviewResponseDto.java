package org.letscareer.letscareer.domain.review.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.review.entity.VWReview;
import org.letscareer.letscareer.domain.review.vo.ChallengeReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetReviewResponseDto(
        List<VWReview> reviewList
) {
    public static GetReviewResponseDto of(List<VWReview> reviewList) {
        return GetReviewResponseDto.builder()
                .reviewList(reviewList)
                .build();
    }
}
