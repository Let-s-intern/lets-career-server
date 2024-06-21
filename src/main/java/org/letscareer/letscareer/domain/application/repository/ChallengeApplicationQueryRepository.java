package org.letscareer.letscareer.domain.application.repository;

import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationVo;
import org.letscareer.letscareer.domain.application.vo.UserChallengeApplicationVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ChallengeApplicationQueryRepository {
    List<AdminChallengeApplicationVo> findAdminChallengeApplicationVos(Long challengeId, Boolean isConfirmed);

    Page<UserChallengeApplicationVo> findUserChallengeApplicationVo(Long challengeId, Pageable pageable);

    Optional<Long> findChallengeApplicationIdByUserIdAndChallengeId(Long userId, Long challengeId);

    Optional<Long> findChallengeApplicationIdByChallengeIdAndUserIdAndIsConfirmed(Long challengeId, Long userId, Boolean isConfirmed);

    List<String> findAllEmailByChallengeIdAndPaymentIsConfirmed(Long challengeId, Boolean isConfirmed);
}
