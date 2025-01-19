package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.review.vo.old.ReviewAdminVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeAdminReviewResponseDto(
        List<ReviewAdminVo> reviewList,
        PageInfo pageInfo
) {
    public static GetChallengeAdminReviewResponseDto of(Page<ReviewAdminVo> challengeReviewVos) {
        PageInfo pageInfo = PageInfo.of(challengeReviewVos);
        return GetChallengeAdminReviewResponseDto.builder()
                .reviewList(challengeReviewVos.getContent())
                .pageInfo(pageInfo)
                .build();
    }
}
