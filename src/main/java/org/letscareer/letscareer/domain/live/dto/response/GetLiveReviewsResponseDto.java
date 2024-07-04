package org.letscareer.letscareer.domain.live.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewResponseDto;
import org.letscareer.letscareer.domain.review.vo.ReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetLiveReviewsResponseDto(
        List<GetReviewResponseDto> reviewList,
        PageInfo pageInfo
) {
    public static GetLiveReviewsResponseDto of(List<GetReviewResponseDto> reviewResDtoList, PageInfo pageInfo) {
        return GetLiveReviewsResponseDto.builder()
                .reviewList(reviewResDtoList)
                .pageInfo(pageInfo)
                .build();
    }
}
