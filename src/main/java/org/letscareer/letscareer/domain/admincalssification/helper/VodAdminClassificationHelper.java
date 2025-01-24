package org.letscareer.letscareer.domain.admincalssification.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.admincalssification.entity.VodAdminClassification;
import org.letscareer.letscareer.domain.admincalssification.repository.VodAdminClassificationRepository;
import org.letscareer.letscareer.domain.admincalssification.request.CreateVodAdminClassificationRequestDto;
import org.letscareer.letscareer.domain.admincalssification.vo.VodAdminClassificationDetailVo;
import org.letscareer.letscareer.domain.vod.entity.Vod;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class VodAdminClassificationHelper {
    private final VodAdminClassificationRepository vodClassificationRepository;

    public VodAdminClassification createVodAdminClassificationAndSave(CreateVodAdminClassificationRequestDto requestDto,
                                                                      Vod vod) {
        VodAdminClassification vodClassification = VodAdminClassification.createVodAdminClassification(requestDto, vod);
        return vodClassificationRepository.save(vodClassification);
    }

    public List<VodAdminClassificationDetailVo> findVodAdminClassificationVos(Long vodId) {
        return vodClassificationRepository.findVodClassificationDetailVos(vodId);
    }

    public void deleteVodAdminClassificationsByVodId(Long vodId) {
        vodClassificationRepository.deleteAllByVodId(vodId);
    }
}
