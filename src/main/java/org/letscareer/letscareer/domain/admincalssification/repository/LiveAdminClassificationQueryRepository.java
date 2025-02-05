package org.letscareer.letscareer.domain.admincalssification.repository;

import org.letscareer.letscareer.domain.admincalssification.vo.LiveAdminClassificationDetailVo;

import java.util.List;

public interface LiveAdminClassificationQueryRepository {
    List<LiveAdminClassificationDetailVo> findLiveClassificationVos(Long liveId);
}
