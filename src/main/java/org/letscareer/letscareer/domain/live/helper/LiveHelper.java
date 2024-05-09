package org.letscareer.letscareer.domain.live.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.live.dto.request.CreateLiveRequestDto;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.live.repository.LiveRepository;
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
}
