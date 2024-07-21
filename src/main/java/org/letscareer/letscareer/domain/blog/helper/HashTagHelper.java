package org.letscareer.letscareer.domain.blog.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.entity.HashTag;
import org.letscareer.letscareer.domain.blog.repository.HashTagRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HashTagHelper {
    private final HashTagRepository hashTagRepository;

    public void saveHashTag(HashTag hashTag) {
        hashTagRepository.save(hashTag);
    }
}
