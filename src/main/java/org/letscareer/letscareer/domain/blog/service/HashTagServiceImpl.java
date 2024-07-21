package org.letscareer.letscareer.domain.blog.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.dto.request.CreateHashTagRequestDto;
import org.letscareer.letscareer.domain.blog.entity.HashTag;
import org.letscareer.letscareer.domain.blog.helper.HashTagHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class HashTagServiceImpl implements HashTagService {
    private final HashTagHelper hashTagHelper;

    @Override
    public void createHashTag(CreateHashTagRequestDto requestDto) {
        HashTag newHashTag = HashTag.createHashTag(requestDto);
        hashTagHelper.saveHashTag(newHashTag);
    }
}
