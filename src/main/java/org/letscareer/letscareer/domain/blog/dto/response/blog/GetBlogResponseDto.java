package org.letscareer.letscareer.domain.blog.dto.response.blog;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.blog.vo.BlogDetailVo;
import org.letscareer.letscareer.domain.blog.vo.HashTagDetailInfo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetBlogResponseDto(
        BlogDetailVo blogDetailInfo,
        List<HashTagDetailInfo> tagDetailInfos
) {
    public static GetBlogResponseDto of(BlogDetailVo blogDetailInfo,
                                        List<HashTagDetailInfo> tagDetailInfos) {
        return GetBlogResponseDto.builder()
                .blogDetailInfo(blogDetailInfo)
                .tagDetailInfos(tagDetailInfos)
                .build();
    }
}
