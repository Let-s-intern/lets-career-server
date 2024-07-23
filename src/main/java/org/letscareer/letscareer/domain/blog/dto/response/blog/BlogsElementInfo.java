package org.letscareer.letscareer.domain.blog.dto.response.blog;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.blog.vo.BlogThumbnailVo;
import org.letscareer.letscareer.domain.blog.vo.HashTagDetailInfo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record BlogsElementInfo(
        BlogThumbnailVo blogThumbnailInfo,
        List<HashTagDetailInfo> tagDetailInfos
) {
    public static BlogsElementInfo of(BlogThumbnailVo blogThumbnailInfo,
                                      List<HashTagDetailInfo> tagDetailInfos) {
        return BlogsElementInfo.builder()
                .blogThumbnailInfo(blogThumbnailInfo)
                .tagDetailInfos(tagDetailInfos)
                .build();
    }
}
