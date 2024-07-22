package org.letscareer.letscareer.domain.blog.service;

import org.letscareer.letscareer.domain.blog.dto.request.CreateHashTagRequestDto;
import org.letscareer.letscareer.domain.blog.dto.request.UpdateHashTagRequestDto;
import org.letscareer.letscareer.domain.blog.dto.response.tag.GetTagsResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface HashTagService {
    GetTagsResponseDto getTags();
    void createHashTag(CreateHashTagRequestDto requestDto);
    void updateHashTag(Long tagId, UpdateHashTagRequestDto requestDto);
    void deleteHashTag(Long tagId);
}
