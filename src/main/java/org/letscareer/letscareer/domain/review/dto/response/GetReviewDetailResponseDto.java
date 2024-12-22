package org.letscareer.letscareer.domain.review.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.review.vo.ReviewDetailVo;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record GetReviewDetailResponseDto(
        Long id,
        Long applicationId,
        Integer nps,
        String npsAns,
        Boolean npsCheckAns,
        String content,
        Integer score,
        LocalDateTime createdDate
) {
    public static GetReviewDetailResponseDto of(ReviewDetailVo reviewVo) {
        return GetReviewDetailResponseDto.builder()
                .id(reviewVo.id())
                .applicationId(reviewVo.applicationId())
                .nps(reviewVo.nps())
                .npsAns(reviewVo.npsAns())
                .npsCheckAns(reviewVo.npsCheckAns())
                .content(reviewVo.content())
                .score(reviewVo.score())
                .createdDate(reviewVo.createdDate())
                .build();
    }
}
