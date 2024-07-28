package org.letscareer.letscareer.domain.blog.mapper;

import org.letscareer.letscareer.domain.blog.dto.response.rating.GetRatingsResponseDto;
import org.letscareer.letscareer.domain.blog.vo.RatingDetailVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RatingMapper {
    public GetRatingsResponseDto toGetRatingsResponseDto(List<RatingDetailVo> ratingInfos) {
        return GetRatingsResponseDto.of(ratingInfos);
    }
}
