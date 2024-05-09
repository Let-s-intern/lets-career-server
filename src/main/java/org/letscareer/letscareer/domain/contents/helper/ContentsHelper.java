package org.letscareer.letscareer.domain.contents.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.contents.dto.request.CreateContentsRequestDto;
import org.letscareer.letscareer.domain.contents.entity.Contents;
import org.letscareer.letscareer.domain.contents.mapper.ContentsMapper;
import org.letscareer.letscareer.domain.contents.repository.ContentsRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class ContentsHelper {
    private final ContentsMapper contentsMapper;
    private final ContentsRepository contentsRepository;

    public void createContentsAndSave(CreateContentsRequestDto createContentsRequestDto) {
        Contents newContents = contentsMapper.toEntity(createContentsRequestDto);
        contentsRepository.save(newContents);
    }
}
