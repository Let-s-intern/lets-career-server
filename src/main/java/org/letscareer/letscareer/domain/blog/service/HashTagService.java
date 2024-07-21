package org.letscareer.letscareer.domain.blog.service;

import org.letscareer.letscareer.domain.blog.dto.request.CreateHashTagRequestDto;
import org.letscareer.letscareer.domain.blog.dto.response.tag.GetTagsResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface HashTagService {
    void createHashTag(CreateHashTagRequestDto requestDto);
    GetTagsResponseDto getTags();
}
