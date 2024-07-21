package org.letscareer.letscareer.domain.blog.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.dto.request.CreateHashTagRequestDto;
import org.letscareer.letscareer.domain.blog.dto.response.tag.GetTagsResponseDto;
import org.letscareer.letscareer.domain.blog.entity.HashTag;
import org.letscareer.letscareer.domain.blog.helper.HashTagHelper;
import org.letscareer.letscareer.domain.blog.mapper.HashTagMapper;
import org.letscareer.letscareer.domain.blog.vo.HashTagDetailInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class HashTagServiceImpl implements HashTagService {
    private final HashTagHelper hashTagHelper;
    private final HashTagMapper hashTagMapper;

    @Override
    public void createHashTag(CreateHashTagRequestDto requestDto) {
        HashTag newHashTag = HashTag.createHashTag(requestDto);
        hashTagHelper.saveHashTag(newHashTag);
    }

    @Override
    public GetTagsResponseDto getTags() {
        List<HashTagDetailInfo> tagDetailInfos = hashTagHelper.getTagDetailInfos();
        return hashTagMapper.toGetTagsResponseDto(tagDetailInfos);
    }
}
