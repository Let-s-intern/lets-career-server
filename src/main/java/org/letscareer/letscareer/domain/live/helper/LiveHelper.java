package org.letscareer.letscareer.domain.live.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.live.dto.request.CreateLiveRequestDto;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.live.error.LiveErrorCode;
import org.letscareer.letscareer.domain.live.repository.LiveRepository;
import org.letscareer.letscareer.domain.live.vo.*;
import org.letscareer.letscareer.domain.program.dto.response.ZoomMeetingResponseDto;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LiveHelper {
    private final LiveRepository liveRepository;

    public Live createLiveAndSave(CreateLiveRequestDto requestDto, ZoomMeetingResponseDto zoomMeetingInfo) {
        Live live = Live.createLive(requestDto, zoomMeetingInfo);
        return liveRepository.save(live);
    }

    public Live findLiveByIdOrThrow(Long liveId) {
        return liveRepository.findById(liveId)
                .orElseThrow(() -> new EntityNotFoundException(LiveErrorCode.LIVE_NOT_FOUND));
    }

    public LiveDetailVo findLiveDetailVoOrThrow(Long liveId) {
        return liveRepository.findLiveDetailVo(liveId)
                .orElseThrow(() -> new EntityNotFoundException(LiveErrorCode.LIVE_NOT_FOUND));
    }

    public Page<LiveProfileVo> findLiveProfileVos(List<ProgramClassification> typeList, List<ProgramStatusType> statusList, Pageable pageable) {
        return liveRepository.findLiveProfileVos(typeList, statusList, pageable);
    }

    public LiveThumbnailVo findLiveThumbnailVoOrThrow(Long liveId) {
        return liveRepository.findLiveThumbnailVo(liveId)
                .orElseThrow(() -> new EntityNotFoundException(LiveErrorCode.LIVE_NOT_FOUND));
    }

    public LiveContentVo findLiveContentVoOrThrow(Long liveId) {
        return liveRepository.findLiveContentVo(liveId)
                .orElseThrow(() -> new EntityNotFoundException(LiveErrorCode.LIVE_NOT_FOUND));
    }

    public LiveApplicationFormVo findLiveApplicationFormVoOrThrow(Long liveId) {
        return liveRepository.findLiveApplicationFormVo(liveId)
                .orElseThrow(() -> new EntityNotFoundException(LiveErrorCode.LIVE_NOT_FOUND));
    }

    public void deleteLiveById(Long liveId) {
        liveRepository.deleteById(liveId);
    }
}
