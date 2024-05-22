package org.letscareer.letscareer.domain.live.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetLiveReviewsResponseDto(
        List<ReviewVo> reviewList,
        PageInfo pageInfo
) {
    public static GetLiveReviewsResponseDto of(Page<ReviewVo> reviewVos) {
        PageInfo pageInfo = PageInfo.of(reviewVos);
        return GetLiveReviewsResponseDto.builder()
                .reviewList(reviewVos.getContent())
                .pageInfo(pageInfo)
                .build();
    }
}
