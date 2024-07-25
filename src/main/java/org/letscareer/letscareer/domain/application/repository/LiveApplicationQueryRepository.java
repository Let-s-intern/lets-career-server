package org.letscareer.letscareer.domain.application.repository;

import org.letscareer.letscareer.domain.application.entity.LiveApplication;
import org.letscareer.letscareer.domain.application.vo.AdminLiveApplicationVo;
import org.letscareer.letscareer.domain.live.vo.LiveEmailVo;

import java.util.List;
import java.util.Optional;

public interface LiveApplicationQueryRepository {
    Optional<LiveApplication> findLiveApplicationByLiveIdAndUserId(Long liveId, Long userId);
    List<AdminLiveApplicationVo> findAdminLiveApplicationVos(Long liveId, Boolean isConfirmed);

    LiveEmailVo findLiveEmailVoByApplicationId(Long applicationId);

    List<String> findEmailListByLiveId(Long liveId);

    Optional<Long> findLiveApplicationIdByUserIdAndLiveId(Long userId, Long liveId);

    List<String> findQuestionListByLiveId(Long liveId);

    List<String> findMotivateListByLiveId(Long liveId);

    Long countByLiveId(Long liveId);
}
