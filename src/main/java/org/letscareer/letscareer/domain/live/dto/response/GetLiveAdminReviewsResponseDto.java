package org.letscareer.letscareer.domain.live.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.review.vo.ReviewAdminVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetLiveAdminReviewsResponseDto(
        List<ReviewAdminVo> reviewList,
        PageInfo pageInfo
) {
    public static GetLiveAdminReviewsResponseDto of(Page<ReviewAdminVo> reviewVos) {
        PageInfo pageInfo = PageInfo.of(reviewVos);
        return GetLiveAdminReviewsResponseDto.builder()
                .reviewList(reviewVos.getContent())
                .pageInfo(pageInfo)
                .build();
    }
}
