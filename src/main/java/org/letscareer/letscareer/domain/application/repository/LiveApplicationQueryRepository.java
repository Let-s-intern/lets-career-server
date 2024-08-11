package org.letscareer.letscareer.domain.application.repository;

import org.letscareer.letscareer.domain.application.entity.LiveApplication;
import org.letscareer.letscareer.domain.application.vo.AdminLiveApplicationVo;
import org.letscareer.letscareer.domain.application.vo.ReviewNotificationUserVo;
import org.letscareer.letscareer.domain.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface LiveApplicationQueryRepository {
    Optional<LiveApplication> findLiveApplicationByLiveIdAndUserId(Long liveId, Long userId);
    List<AdminLiveApplicationVo> findAdminLiveApplicationVos(Long liveId, Boolean isConfirmed);

    Optional<Long> findLiveApplicationIdByUserIdAndLiveId(Long userId, Long liveId);

    List<String> findQuestionListByLiveId(Long liveId);

    List<String> findMotivateListByLiveId(Long liveId);

    Long countByLiveId(Long liveId);

    List<ReviewNotificationUserVo> findAllReviewNotificationUserVo(Long liveId);

    List<User> findAllRemindNotificationUser(Long liveId);
}
