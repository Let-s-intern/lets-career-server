package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.review.dto.response.GetOldReviewResponseDto;
import org.letscareer.letscareer.global.common.entity.PageInfo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeReviewResponseDto(
        List<GetOldReviewResponseDto> reviewList,
        PageInfo pageInfo
) {
    public static GetChallengeReviewResponseDto of(List<GetOldReviewResponseDto> reviewResDtoList,
                                                   PageInfo pageInfo) {
        return GetChallengeReviewResponseDto.builder()
                .reviewList(reviewResDtoList)
                .pageInfo(pageInfo)
                .build();
    }
}
