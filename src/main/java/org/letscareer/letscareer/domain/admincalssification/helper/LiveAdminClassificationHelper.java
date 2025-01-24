package org.letscareer.letscareer.domain.admincalssification.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.admincalssification.entity.LiveAdminClassification;
import org.letscareer.letscareer.domain.admincalssification.repository.LiveAdminClassificationRepository;
import org.letscareer.letscareer.domain.admincalssification.request.CreateLiveAdminClassificationRequestDto;
import org.letscareer.letscareer.domain.admincalssification.vo.LiveAdminClassificationVo;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class LiveAdminClassificationHelper {
    private final LiveAdminClassificationRepository liveClassificationRepository;

    public LiveAdminClassification createLiveClassificationAndSave(CreateLiveAdminClassificationRequestDto requestDto,
                                                                   Live live) {
        LiveAdminClassification liveClassification = LiveAdminClassification.createLiveAdminClassification(requestDto, live);
        return liveClassificationRepository.save(liveClassification);
    }

    public List<LiveAdminClassificationVo> findLiveClassificationVos(Long liveId) {
        return liveClassificationRepository.findLiveClassificationVos(liveId);
    }

    public void deleteLiveClassificationsByLiveId(Long liveId) {
        liveClassificationRepository.deleteAllByLiveId(liveId);
    }
}
