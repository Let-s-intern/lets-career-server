package org.letscareer.letscareer.domain.vod.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.vod.dto.request.CreateVodRequestDto;
import org.letscareer.letscareer.domain.vod.entity.Vod;
import org.letscareer.letscareer.domain.vod.repository.VodRepository;
import org.letscareer.letscareer.domain.vod.vo.VodDetailVo;
import org.letscareer.letscareer.domain.vod.vo.VodProfileVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.letscareer.letscareer.domain.vod.error.VodErrorCode.VOD_NOT_FOUND;

@RequiredArgsConstructor
@Transactional
@Component
public class VodHelper {
    private final VodRepository vodRepository;

    public Vod createVodAndSave(CreateVodRequestDto requestDto) {
        Vod vod = Vod.createVod(requestDto);
        return vodRepository.save(vod);
    }

    public VodDetailVo findVodDetailVoOrThrow(Long vodId) {
        return vodRepository.findVodDetailVo(vodId)
                .orElseThrow(() -> new EntityNotFoundException(VOD_NOT_FOUND));
    }

    public Vod findVodByIdOrThrow(Long vodId) {
        return vodRepository.findById(vodId)
                .orElseThrow(() -> new EntityNotFoundException(VOD_NOT_FOUND));
    }

    public Page<VodProfileVo> findVodProfileVos(ProgramClassification type, Pageable pageable) {
        return vodRepository.findVodProfileVos(type, pageable);
    }

    public void deleteVodById(Long vodId) {
        vodRepository.deleteById(vodId);
    }
}
