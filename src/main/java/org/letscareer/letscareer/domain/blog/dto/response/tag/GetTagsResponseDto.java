package org.letscareer.letscareer.domain.blog.dto.response.tag;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.blog.vo.HashTagDetailInfo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetTagsResponseDto(
        List<HashTagDetailInfo> tagDetailInfos
) {
    public static GetTagsResponseDto of(List<HashTagDetailInfo> tagDetailInfos) {
        return GetTagsResponseDto.builder()
                .tagDetailInfos(tagDetailInfos)
                .build();
    }
}
