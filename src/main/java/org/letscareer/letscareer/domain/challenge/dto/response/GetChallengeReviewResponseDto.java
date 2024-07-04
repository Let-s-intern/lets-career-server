package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewResponseDto;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeReviewResponseDto(
        List<GetReviewResponseDto> reviewList,
        PageInfo pageInfo
) {
    public static GetChallengeReviewResponseDto of(List<GetReviewResponseDto> reviewResDtoList,
                                                   PageInfo pageInfo) {
        return GetChallengeReviewResponseDto.builder()
                .reviewList(reviewResDtoList)
                .pageInfo(pageInfo)
                .build();
    }
}
