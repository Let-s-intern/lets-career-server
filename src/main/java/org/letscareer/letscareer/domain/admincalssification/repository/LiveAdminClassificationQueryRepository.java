package org.letscareer.letscareer.domain.admincalssification.repository;

import org.letscareer.letscareer.domain.admincalssification.vo.LiveAdminClassificationVo;

import java.util.List;

public interface LiveAdminClassificationQueryRepository {
    List<LiveAdminClassificationVo> findLiveClassificationVos(Long liveId);
}
