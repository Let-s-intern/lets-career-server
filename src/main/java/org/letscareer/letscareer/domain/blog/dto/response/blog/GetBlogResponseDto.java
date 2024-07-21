package org.letscareer.letscareer.domain.blog.dto.response.blog;

import org.letscareer.letscareer.domain.blog.vo.BlogDetailVo;
import org.letscareer.letscareer.domain.blog.vo.HashTagDetailInfo;

import java.util.List;

public record GetBlogResponseDto(
        BlogDetailVo blogDetailInfo,
        List<HashTagDetailInfo> tagDetailInfos
) {
}
