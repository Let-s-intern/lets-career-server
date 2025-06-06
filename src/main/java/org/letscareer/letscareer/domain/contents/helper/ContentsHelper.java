package org.letscareer.letscareer.domain.contents.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.contents.dto.request.UpdateContentsRequestDto;
import org.letscareer.letscareer.domain.contents.entity.Contents;
import org.letscareer.letscareer.domain.contents.repository.ContentsRepository;
import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.domain.contents.vo.ContentsAdminSimpleVo;
import org.letscareer.letscareer.domain.contents.vo.ContentsAdminVo;
import org.letscareer.letscareer.domain.contents.vo.ContentsDetailVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.letscareer.letscareer.domain.contents.error.ContentsErrorCode.CONTENTS_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class ContentsHelper {
    private final ContentsRepository contentsRepository;

    public Contents findContentsByIdOrThrow(Long contentsId) {
        return contentsRepository.findById(contentsId).orElseThrow(() -> new EntityNotFoundException(CONTENTS_NOT_FOUND));
    }

    public ContentsDetailVo findContentsDetailVoOrThrow(Long contentsId) {
        return contentsRepository.findContentsDetailVo(contentsId)
                .orElseThrow(() -> new EntityNotFoundException(CONTENTS_NOT_FOUND));
    }

    public void saveContents(Contents contents) {
        contentsRepository.save(contents);
    }

    public Page<ContentsAdminVo> findAllContentsAdminVos(Pageable pageable) {
        return contentsRepository.findAllContentsAdminVos(pageable);
    }

    public List<ContentsAdminSimpleVo> findAllContentsAdminSimpleVos(ContentsType contentsType) {
        return contentsRepository.findAllContentsAdminSimpleVos(contentsType);
    }

    public void deleteContentsById(Long contentsId) {
        contentsRepository.deleteById(contentsId);
    }
}
