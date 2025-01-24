package org.letscareer.letscareer.domain.admincalssification.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.admincalssification.entity.LiveAdminClassification;
import org.letscareer.letscareer.domain.admincalssification.repository.LiveAdminClassificationRepository;
import org.letscareer.letscareer.domain.admincalssification.request.CreateLiveAdminClassificationRequestDto;
import org.letscareer.letscareer.domain.admincalssification.vo.LiveAdminClassificationDetailVo;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class LiveAdminClassificationHelper {
    private final LiveAdminClassificationRepository liveClassificationRepository;

    public LiveAdminClassification createLiveAdminClassificationAndSave(CreateLiveAdminClassificationRequestDto requestDto,
                                                                        Live live) {
        LiveAdminClassification liveClassification = LiveAdminClassification.createLiveAdminClassification(requestDto, live);
        return liveClassificationRepository.save(liveClassification);
    }

    public List<LiveAdminClassificationDetailVo> findAdminClassificationDetailVos(Long liveId) {
        return liveClassificationRepository.findLiveClassificationVos(liveId);
    }

    public void deleteLiveAdminClassificationsByLiveId(Long liveId) {
        liveClassificationRepository.deleteAllByLiveId(liveId);
    }
}
