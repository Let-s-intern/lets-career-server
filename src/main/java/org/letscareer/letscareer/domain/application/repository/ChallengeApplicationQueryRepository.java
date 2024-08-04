package org.letscareer.letscareer.domain.application.repository;

import org.letscareer.letscareer.domain.application.entity.ChallengeApplication;
import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationVo;
import org.letscareer.letscareer.domain.application.vo.UserChallengeApplicationVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ChallengeApplicationQueryRepository {
    Optional<ChallengeApplication> findChallengeApplicationByChallengeIdAndUserId(Long challengeId, Long userId);

    List<AdminChallengeApplicationVo> findAdminChallengeApplicationVos(Long challengeId, Boolean isCanceled);

    Page<UserChallengeApplicationVo> findUserChallengeApplicationVo(Long challengeId, Pageable pageable);

    Optional<Long> findChallengeApplicationIdByUserIdAndChallengeId(Long userId, Long challengeId);

    Optional<Long> findChallengeApplicationIdByChallengeIdAndUserIdAndIsCanceled(Long challengeId, Long userId, Boolean isCanceled);

    List<String> findAllEmailByChallengeIdAndIsCanceled(Long challengeId, Boolean isCanceled);

    Long findApplicationIdByChallengeIdAndUserId(Long challengeId, Long userId);

    Long countByChallengeId(Long challengeId);

    List<User> findAllReviewNotificationUser(Long challengeId);

    List<User> findAllNotificationUser(Long challengeId);

    List<User> findAllAttendanceNullNotificationUser(Long challengeId, Long missionId);
}
