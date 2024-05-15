package org.letscareer.letscareer.domain.contents.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.contents.dto.request.UpdateContentsRequestDto;
import org.letscareer.letscareer.domain.contents.entity.Contents;
import org.letscareer.letscareer.domain.contents.repository.ContentsRepository;
import org.letscareer.letscareer.domain.contents.vo.ContentsAdminVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ContentsHelper {
    private final ContentsRepository contentsRepository;

    public Contents findContentsByIdOrThrow(Long contentsId) {
        return contentsRepository.findById(contentsId).orElseThrow(EntityNotFoundException::new);
    }

    public Contents findContentsByIdOrNull(Long contentsId) {
        return contentsRepository.findById(contentsId).orElse(null);
    }

    public void saveContents(Contents contents) {
        contentsRepository.save(contents);
    }

    public Page<ContentsAdminVo> findAllContentsAdminVos(Pageable pageable) {
        return contentsRepository.findAllContentsAdminVos(pageable);
    }

    public void updateContents(Long contentsId, UpdateContentsRequestDto updateContentsRequestDto) {
        Contents contents = findContentsByIdOrThrow(contentsId);
        contents.updateContents(updateContentsRequestDto);
    }
}
