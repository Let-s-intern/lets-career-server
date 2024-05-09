package org.letscareer.letscareer.domain.live.service;

import org.letscareer.letscareer.domain.live.dto.request.CreateLiveRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface LiveService {
    void createLive(CreateLiveRequestDto requestDto);
}
