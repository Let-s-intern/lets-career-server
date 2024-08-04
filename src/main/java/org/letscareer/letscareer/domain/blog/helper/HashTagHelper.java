package org.letscareer.letscareer.domain.blog.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.dto.request.CreateHashTagRequestDto;
import org.letscareer.letscareer.domain.blog.entity.HashTag;
import org.letscareer.letscareer.domain.blog.repository.HashTagRepository;
import org.letscareer.letscareer.domain.blog.vo.HashTagDetailInfo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.letscareer.letscareer.domain.blog.error.BlogErrorCode.HASHTAG_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class HashTagHelper {
    private final HashTagRepository hashTagRepository;

    public HashTag createAndSaveHashTag(CreateHashTagRequestDto requestDto) {
        HashTag newHashTag = HashTag.createHashTag(requestDto);
        return hashTagRepository.save(newHashTag);
    }

    public List<HashTagDetailInfo> findTagDetailInfos(Long blogId) {
        return hashTagRepository.findAllHashTagDetailInfo(blogId);
    }

    public HashTag findHashTagByTagId(Long tagId) {
        return hashTagRepository.findById(tagId).orElseThrow(() -> new EntityNotFoundException(HASHTAG_NOT_FOUND));
    }

    public List<HashTag> findHashTags(List<Long> tagList) {
        return tagList.stream()
                .map(this::findHashTagByTagId)
                .collect(Collectors.toList());
    }

    public void deleteHashTag(HashTag hashTag) {
        hashTagRepository.delete(hashTag);
    }
}
