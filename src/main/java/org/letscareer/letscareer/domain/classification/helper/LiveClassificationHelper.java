package org.letscareer.letscareer.domain.classification.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.classification.dto.request.CreateLiveClassificationRequestDto;
import org.letscareer.letscareer.domain.classification.entity.LiveClassification;
import org.letscareer.letscareer.domain.classification.repository.LiveClassificationRepository;
import org.letscareer.letscareer.domain.classification.vo.LiveClassificationVo;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class LiveClassificationHelper {
    private final LiveClassificationRepository liveClassificationRepository;

    public LiveClassification createLiveClassificationAndSave(CreateLiveClassificationRequestDto requestDto,
                                                              Live live) {
        LiveClassification liveClassification = LiveClassification.createLiveClassification(requestDto, live);
        return liveClassificationRepository.save(liveClassification);
    }

    public List<LiveClassificationVo> findLiveClassificationVos(Long liveId) {
        return liveClassificationRepository.findLiveClassificationVos(liveId);
    }

    public void deleteLiveClassificationsByLiveId(Long liveId) {
        liveClassificationRepository.deleteAllByLiveId(liveId);
    }
}
