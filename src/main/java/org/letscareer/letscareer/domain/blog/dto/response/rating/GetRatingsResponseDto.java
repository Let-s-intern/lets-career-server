package org.letscareer.letscareer.domain.blog.dto.response.rating;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.blog.vo.RatingDetailVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetRatingsResponseDto(
        List<RatingDetailVo> ratingInfos
) {
    public static GetRatingsResponseDto of(List<RatingDetailVo> ratingInfos) {
        return GetRatingsResponseDto.builder()
                .ratingInfos(ratingInfos)
                .build();
    }
}
