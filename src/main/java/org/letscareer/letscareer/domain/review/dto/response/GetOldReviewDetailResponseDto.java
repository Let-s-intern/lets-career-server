package org.letscareer.letscareer.domain.review.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.review.vo.ReviewDetailVo;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record GetOldReviewDetailResponseDto(
        Long id,
        Long applicationId,
        Long userId,
        Integer nps,
        String npsAns,
        Boolean npsCheckAns,
        String content,
        Integer score,
        String programDetail,
        LocalDateTime createdDate
) {
    public static GetOldReviewDetailResponseDto of(ReviewDetailVo reviewVo) {
        return GetOldReviewDetailResponseDto.builder()
                .id(reviewVo.id())
                .applicationId(reviewVo.applicationId())
                .userId(reviewVo.userId())
                .nps(reviewVo.nps())
                .npsAns(reviewVo.npsAns())
                .npsCheckAns(reviewVo.npsCheckAns())
                .content(reviewVo.content())
                .score(reviewVo.score())
                .programDetail(reviewVo.programDetail())
                .createdDate(reviewVo.createdDate())
                .build();
    }
}
