package org.letscareer.letscareer.domain.live.service;

import org.letscareer.letscareer.domain.application.dto.response.GetLiveApplicationsResponseDto;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.live.dto.request.CreateLiveRequestDto;
import org.letscareer.letscareer.domain.live.dto.response.GetLiveDetailResponseDto;
import org.letscareer.letscareer.domain.live.dto.response.GetLivesResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface LiveService {
    GetLivesResponseDto getLiveList(ProgramClassification type, Pageable pageable);

    GetLiveDetailResponseDto getLiveDetail(Long liveId);

    GetLiveApplicationsResponseDto getApplications(Long liveId, Boolean isConfirmed);

    void createLive(CreateLiveRequestDto requestDto);

    void updateLive(Long liveId, CreateLiveRequestDto requestDto);

    void deleteLive(Long liveId);
}
