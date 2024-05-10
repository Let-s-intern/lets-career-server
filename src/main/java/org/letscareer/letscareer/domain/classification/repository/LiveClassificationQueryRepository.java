package org.letscareer.letscareer.domain.classification.repository;

import org.letscareer.letscareer.domain.classification.vo.LiveClassificationVo;

import java.util.List;

public interface LiveClassificationQueryRepository {
    List<LiveClassificationVo> findLiveClassificationVos(Long liveId);
}
