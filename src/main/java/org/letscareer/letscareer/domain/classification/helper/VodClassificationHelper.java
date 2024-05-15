package org.letscareer.letscareer.domain.classification.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.classification.dto.request.CreateVodClassificationRequestDto;
import org.letscareer.letscareer.domain.classification.entity.VodClassification;
import org.letscareer.letscareer.domain.classification.repository.VodClassificationRepository;
import org.letscareer.letscareer.domain.classification.vo.VodClassificationDetailVo;
import org.letscareer.letscareer.domain.vod.entity.Vod;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Component
public class VodClassificationHelper {
    private final VodClassificationRepository vodClassificationRepository;

    public VodClassification createVodClassificationAndSave(CreateVodClassificationRequestDto requestDto,
                                                            Vod vod) {
        VodClassification vodClassification = VodClassification.createVodClassification(requestDto, vod);
        return vodClassificationRepository.save(vodClassification);
    }

    public List<VodClassificationDetailVo> findVodClassificationVos(Long vodId) {
        return vodClassificationRepository.findVodClassificationDetailVos(vodId);
    }

    public void deleteVodClassificationsByVodId(Long vodId) {
        vodClassificationRepository.deleteAllByVodId(vodId);
    }
}
