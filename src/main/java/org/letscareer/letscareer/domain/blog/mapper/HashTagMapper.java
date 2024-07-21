package org.letscareer.letscareer.domain.blog.mapper;

import org.letscareer.letscareer.domain.blog.dto.response.tag.GetTagsResponseDto;
import org.letscareer.letscareer.domain.blog.vo.HashTagDetailInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HashTagMapper {
    public GetTagsResponseDto toGetTagsResponseDto(List<HashTagDetailInfo> tagDetailInfos) {
        return GetTagsResponseDto.of(tagDetailInfos);
    }
}
