package org.letscareer.letscareer.domain.live.repository;

import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.live.vo.LiveDetailVo;
import org.letscareer.letscareer.domain.live.vo.LiveProfileVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

public interface LiveQueryRepository {
    Optional<LiveDetailVo> findLiveDetailVo(Long liveId);
    Page<LiveProfileVo> findLiveProfileVos(ProgramClassification type, Pageable pageable);
}
