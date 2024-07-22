package org.letscareer.letscareer.domain.blog.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.entity.HashTag;
import org.letscareer.letscareer.domain.blog.repository.HashTagRepository;
import org.letscareer.letscareer.domain.blog.vo.HashTagDetailInfo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class HashTagHelper {
    private final HashTagRepository hashTagRepository;

    public void saveHashTag(HashTag hashTag) {
        hashTagRepository.save(hashTag);
    }

    public List<HashTagDetailInfo> getTagDetailInfos() {
        return hashTagRepository.findAllHashTagDetailInfo();
    }

    public HashTag findHashTagByTagId(Long tagId) {
        return hashTagRepository.findById(tagId).orElseThrow(() -> new EntityNotFoundException());
    }

    public void deleteHashTag(HashTag hashTag) {
        hashTagRepository.delete(hashTag);
    }
}
