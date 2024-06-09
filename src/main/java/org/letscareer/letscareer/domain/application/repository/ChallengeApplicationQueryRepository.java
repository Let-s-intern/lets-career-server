package org.letscareer.letscareer.domain.application.repository;

import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationVo;
import org.letscareer.letscareer.domain.application.vo.UserChallengeApplicationVo;

import java.util.List;
import java.util.Optional;

public interface ChallengeApplicationQueryRepository {
    List<AdminChallengeApplicationVo> findAdminChallengeApplicationVos(Long challengeId, Boolean isConfirmed);

    List<UserChallengeApplicationVo> findUserChallengeApplicationVo(Long challengeId);

    Optional<Long> findChallengeApplicationIdByUserIdAndChallengeId(Long userId, Long challengeId);
}
