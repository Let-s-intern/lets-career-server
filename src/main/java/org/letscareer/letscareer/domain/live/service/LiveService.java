package org.letscareer.letscareer.domain.live.service;

import org.letscareer.letscareer.domain.application.dto.response.GetLiveApplicationsResponseDto;
import org.letscareer.letscareer.domain.live.dto.request.UpdateLiveRequestDto;
import org.letscareer.letscareer.domain.live.dto.response.GetLiveApplicationFormResponseDto;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.faq.dto.response.GetFaqResponseDto;
import org.letscareer.letscareer.domain.live.dto.request.CreateLiveRequestDto;
import org.letscareer.letscareer.domain.live.dto.response.*;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LiveService {
    GetLivesResponseDto getLiveList(List<ProgramClassification> typeList, List<ProgramStatusType> statusList, Pageable pageable);

    GetLiveDetailResponseDto getLiveDetail(Long liveId);

    GetLiveTitleResponseDto getLiveTitle(Long liveId);

    GetLiveThumbnailResponseDto getLiveThumbnail(Long liveId);

    GetLiveContentResponseDto getLiveDetailContent(Long liveId);

    GetFaqResponseDto getLiveFaqs(Long liveId);

    GetLiveApplicationFormResponseDto getLiveApplicationForm(User user, Long liveId);

    GetLiveApplicationsResponseDto getApplications(Long liveId, Boolean isConfirmed);

    GetLiveReviewsResponseDto getReviews(Long liveId, Pageable pageable);

    void createLive(CreateLiveRequestDto requestDto);

    void updateLive(Long liveId, UpdateLiveRequestDto requestDto);

    void deleteLive(Long liveId);
}
