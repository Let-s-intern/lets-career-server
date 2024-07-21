package org.letscareer.letscareer.domain.blog.dto.response.blog;

import org.letscareer.letscareer.domain.blog.vo.BlogThumbnailVo;
import org.letscareer.letscareer.domain.blog.vo.HashTagDetailInfo;

import java.util.List;

public record BlogsElementInfo(
        BlogThumbnailVo blogThumbnailInfo,
        List<HashTagDetailInfo> tagDetailInfos
) {
}
