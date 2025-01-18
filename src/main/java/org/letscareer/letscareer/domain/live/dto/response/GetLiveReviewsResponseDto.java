package org.letscareer.letscareer.domain.live.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.review.dto.response.GetOldReviewResponseDto;
import org.letscareer.letscareer.global.common.entity.PageInfo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetLiveReviewsResponseDto(
        List<GetOldReviewResponseDto> reviewList,
        PageInfo pageInfo
) {
    public static GetLiveReviewsResponseDto of(List<GetOldReviewResponseDto> reviewResDtoList, PageInfo pageInfo) {
        return GetLiveReviewsResponseDto.builder()
                .reviewList(reviewResDtoList)
                .pageInfo(pageInfo)
                .build();
    }
}
