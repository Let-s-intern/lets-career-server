package org.letscareer.letscareer.domain.blog.dto.response.rating;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.blog.vo.RatingDetailVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetBlogRatingsResponseDto(
        List<RatingDetailVo> ratingInfos
) {
    public static GetBlogRatingsResponseDto of(List<RatingDetailVo> ratingInfos) {
        return GetBlogRatingsResponseDto.builder()
                .ratingInfos(ratingInfos)
                .build();
    }
}
