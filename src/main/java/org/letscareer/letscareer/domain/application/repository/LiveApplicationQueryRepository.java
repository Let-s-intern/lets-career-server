package org.letscareer.letscareer.domain.application.repository;

import org.letscareer.letscareer.domain.application.vo.AdminLiveApplicationVo;
import org.letscareer.letscareer.domain.live.vo.LiveEmailVo;

import java.util.List;

public interface LiveApplicationQueryRepository {
    List<AdminLiveApplicationVo> findAdminLiveApplicationVos(Long liveId, Boolean isConfirmed);

    LiveEmailVo findLiveEmailVoByApplicationId(Long applicationId);

    List<String> findEmailListByLiveId(Long liveId);
}
