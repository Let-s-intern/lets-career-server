package org.letscareer.letscareer.domain.blog.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.dto.request.CreateHashTagRequestDto;
import org.letscareer.letscareer.domain.blog.dto.request.UpdateHashTagRequestDto;
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
        hashTagHelper.createAndSaveHashTag(requestDto);
    }

    @Override
    public GetTagsResponseDto getTags() {
        List<HashTagDetailInfo> tagDetailInfos = hashTagHelper.findTagDetailInfos(null);
        return hashTagMapper.toGetTagsResponseDto(tagDetailInfos);
    }

    @Override
    public void updateHashTag(Long tagId, UpdateHashTagRequestDto requestDto) {
        HashTag hashTag = hashTagHelper.findHashTagByTagId(tagId);
        hashTag.updateHashTag(requestDto);
    }

    @Override
    public void deleteHashTag(Long tagId) {
        HashTag hashTag = hashTagHelper.findHashTagByTagId(tagId);
        hashTagHelper.deleteHashTag(hashTag);
    }
}
