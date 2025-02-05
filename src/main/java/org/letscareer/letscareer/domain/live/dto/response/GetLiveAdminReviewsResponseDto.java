package org.letscareer.letscareer.domain.live.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.review.vo.old.OldReviewAdminVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetLiveAdminReviewsResponseDto(
        List<OldReviewAdminVo> reviewList,
        PageInfo pageInfo
) {
    public static GetLiveAdminReviewsResponseDto of(Page<OldReviewAdminVo> reviewVos) {
        PageInfo pageInfo = PageInfo.of(reviewVos);
        return GetLiveAdminReviewsResponseDto.builder()
                .reviewList(reviewVos.getContent())
                .pageInfo(pageInfo)
                .build();
    }
}
