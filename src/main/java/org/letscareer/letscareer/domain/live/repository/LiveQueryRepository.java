package org.letscareer.letscareer.domain.live.repository;

import org.letscareer.letscareer.domain.live.vo.LiveDetailVo;

import java.util.Optional;

public interface LiveQueryRepository {
    Optional<LiveDetailVo> findLiveDetailVo(Long liveId);
}
