package org.letscareer.letscareer.domain.blog.mapper;

import org.letscareer.letscareer.domain.blog.dto.response.rating.GetBlogRatingsResponseDto;
import org.letscareer.letscareer.domain.blog.dto.response.rating.GetRatingsResponseDto;
import org.letscareer.letscareer.domain.blog.vo.RatingDetailVo;
import org.letscareer.letscareer.domain.blog.vo.RatingVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RatingMapper {
    public GetBlogRatingsResponseDto toGetBlogRatingsResponseDto(List<RatingDetailVo> ratingInfos) {
        return GetBlogRatingsResponseDto.of(ratingInfos);
    }

    public GetRatingsResponseDto toGetRatingsResponseDto(Page<RatingVo> ratingInfos) {
        PageInfo pageInfo = PageInfo.of(ratingInfos);
        return GetRatingsResponseDto.of(ratingInfos.getContent(), pageInfo);
    }
}
