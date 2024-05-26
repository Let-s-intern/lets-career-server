package org.letscareer.letscareer.domain.live.service;

import org.letscareer.letscareer.domain.application.dto.response.GetLiveApplicationsResponseDto;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.live.dto.request.CreateLiveRequestDto;
import org.letscareer.letscareer.domain.live.dto.response.GetLiveDetailResponseDto;
import org.letscareer.letscareer.domain.live.dto.response.GetLiveReviewsResponseDto;
import org.letscareer.letscareer.domain.live.dto.response.GetLivesResponseDto;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface LiveService {
    GetLivesResponseDto getLiveList(List<ProgramClassification> typeList, List<ProgramStatusType> statusList, Pageable pageable);

    GetLiveDetailResponseDto getLiveDetail(Long liveId);

    GetLiveApplicationsResponseDto getApplications(Long liveId, Boolean isConfirmed);

    GetLiveReviewsResponseDto getReviews(Long liveId, Pageable pageable);

    void createLive(CreateLiveRequestDto requestDto);

    void updateLive(Long liveId, CreateLiveRequestDto requestDto);

    void deleteLive(Long liveId);
}
