package org.letscareer.letscareer.domain.blog.dto.response.tag;

import org.letscareer.letscareer.domain.blog.vo.HashTagDetailInfo;

import java.util.List;

public record GetTagsResponseDto(
        List<HashTagDetailInfo> tagDetailInfos
) {
}
