package org.letscareer.letscareer.domain.blog.dto.response.like;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetLikedBlogResponseDto(
        List<Long> blogIds
) {
    public static GetLikedBlogResponseDto of(List<Long> blogIds){
        return GetLikedBlogResponseDto.builder()
                .blogIds(blogIds)
                .build();
    }
}
