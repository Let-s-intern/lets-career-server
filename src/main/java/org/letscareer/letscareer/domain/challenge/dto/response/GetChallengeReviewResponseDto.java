package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeReviewResponseDto(
        List<ReviewVo> reviewList,
        PageInfo pageInfo
) {
    public static GetChallengeReviewResponseDto of(Page<ReviewVo> challengeReviewVos) {
        PageInfo pageInfo = PageInfo.of(challengeReviewVos);
        return GetChallengeReviewResponseDto.builder()
                .reviewList(challengeReviewVos.getContent())
                .pageInfo(pageInfo)
                .build();
    }
}
