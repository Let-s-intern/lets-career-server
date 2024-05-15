package org.letscareer.letscareer.domain.application.repository;

import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationVo;

import java.util.List;

public interface ChallengeApplicationQueryRepository {
    List<AdminChallengeApplicationVo> findAdminChallengeApplicationVos(Long challengeId, Boolean isConfirmed);
}
