package org.letscareer.letscareer.domain.live.service;

import org.letscareer.letscareer.domain.application.dto.response.GetLiveApplicationsResponseDto;
import org.letscareer.letscareer.domain.live.dto.request.CreateLiveRequestDto;
import org.letscareer.letscareer.domain.live.dto.response.GetLiveDetailResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface LiveService {
    GetLiveDetailResponseDto getLiveDetail(Long liveId);

    GetLiveApplicationsResponseDto getApplications(Long liveId, Boolean isConfirmed);

    void createLive(CreateLiveRequestDto requestDto);

    void updateLive(Long liveId, CreateLiveRequestDto requestDto);

    void deleteLive(Long liveId);
}
