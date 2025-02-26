package org.letscareer.letscareer.domain.blog.mapper;

import org.letscareer.letscareer.domain.blog.dto.response.like.GetLikedBlogResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LikeMapper {
    public GetLikedBlogResponseDto toGetLikedBlogResponseDto(List<Long> blogIds){
        return GetLikedBlogResponseDto.of(blogIds);
    }
}
