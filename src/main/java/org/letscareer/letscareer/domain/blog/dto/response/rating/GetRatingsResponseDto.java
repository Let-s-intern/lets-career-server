package org.letscareer.letscareer.domain.blog.dto.response.rating;

import org.letscareer.letscareer.domain.blog.vo.RatingDetailVo;

import java.util.List;

public record GetRatingsResponseDto(
        List<RatingDetailVo> ratingInfos
) {
}
