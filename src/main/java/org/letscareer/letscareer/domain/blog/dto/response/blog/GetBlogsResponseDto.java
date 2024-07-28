package org.letscareer.letscareer.domain.blog.dto.response.blog;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.global.common.entity.PageInfo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetBlogsResponseDto(
        List<BlogsElementInfo> blogInfos,
        PageInfo pageInfo
) {
    public static GetBlogsResponseDto of(List<BlogsElementInfo> blogInfos,
                                         PageInfo pageInfo) {
        return GetBlogsResponseDto.builder()
                .blogInfos(blogInfos)
                .pageInfo(pageInfo)
                .build();
    }
}
