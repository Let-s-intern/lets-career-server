package org.letscareer.letscareer.domain.live.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.live.dto.request.CreateLiveRequestDto;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.live.error.LiveErrorCode;
import org.letscareer.letscareer.domain.live.repository.LiveRepository;
import org.letscareer.letscareer.domain.live.vo.LiveDetailVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class LiveHelper {
    private final LiveRepository liveRepository;

    public Live createLiveAndSave(CreateLiveRequestDto requestDto) {
        Live live = Live.createLive(requestDto);
        return liveRepository.save(live);
    }

    public LiveDetailVo findLiveDetailVoOrThrow(Long liveId) {
        return liveRepository.findLiveDetailVo(liveId)
                .orElseThrow(() -> new EntityNotFoundException(LiveErrorCode.LIVE_NOT_FOUND));
    }
}
