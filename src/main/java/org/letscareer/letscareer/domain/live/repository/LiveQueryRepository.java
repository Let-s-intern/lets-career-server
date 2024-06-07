package org.letscareer.letscareer.domain.live.repository;

import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.live.vo.*;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LiveQueryRepository {
    Optional<LiveDetailVo> findLiveDetailVo(Long liveId);

    Page<LiveProfileVo> findLiveProfileVos(List<ProgramClassification> typeList, List<ProgramStatusType> statusList, Pageable pageable);

    Optional<LiveTitleVo> findLiveTitleVo(Long liveId);

    Optional<LiveThumbnailVo> findLiveThumbnailVo(Long liveId);

    Optional<LiveContentVo> findLiveContentVo(Long liveId);

    Optional<LiveApplicationFormVo> findLiveApplicationFormVo(Long liveId);
}
