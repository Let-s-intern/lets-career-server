package org.letscareer.letscareer.domain.blog.dto.response.rating;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.blog.vo.RatingVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetRatingsResponseDto(
        List<RatingVo> ratingInfos,
        PageInfo pageInfo
) {
    public static GetRatingsResponseDto of(List<RatingVo> ratingInfos,
                                           PageInfo pageInfo) {
        return GetRatingsResponseDto.builder()
                .ratingInfos(ratingInfos)
                .pageInfo(pageInfo)
                .build();
    }
}
