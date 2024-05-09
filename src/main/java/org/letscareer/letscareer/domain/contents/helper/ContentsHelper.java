package org.letscareer.letscareer.domain.contents.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.contents.dto.request.CreateContentsRequestDto;
import org.letscareer.letscareer.domain.contents.dto.request.UpdateContentsRequestDto;
import org.letscareer.letscareer.domain.contents.entity.Contents;
import org.letscareer.letscareer.domain.contents.mapper.ContentsMapper;
import org.letscareer.letscareer.domain.contents.repository.ContentsRepository;
import org.letscareer.letscareer.domain.contents.vo.ContentsAdminVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class ContentsHelper {
    private final ContentsMapper contentsMapper;
    private final ContentsRepository contentsRepository;

    public Contents findContentsByIdOrThrow(Long contentsId) {
        return contentsRepository.findById(contentsId).orElseThrow(EntityNotFoundException::new);
    }

    public void createContentsAndSave(CreateContentsRequestDto createContentsRequestDto) {
        Contents newContents = contentsMapper.toEntity(createContentsRequestDto);
        contentsRepository.save(newContents);
    }

    public Page<ContentsAdminVo> findAllContentsAdminVos(Pageable pageable) {
        return contentsRepository.findAllContentsAdminVos(pageable);
    }

    public void updateContents(Long contentsId, UpdateContentsRequestDto updateContentsRequestDto) {
        Contents contents = findContentsByIdOrThrow(contentsId);
        contents.updateContents(updateContentsRequestDto);
    }
}
