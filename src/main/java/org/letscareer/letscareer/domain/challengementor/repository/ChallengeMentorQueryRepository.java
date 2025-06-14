package org.letscareer.letscareer.domain.challengementor.repository;

import org.letscareer.letscareer.domain.challengementor.vo.ChallengeMentorAdminVo;

import java.util.List;

public interface ChallengeMentorQueryRepository {
    List<ChallengeMentorAdminVo> findChallengeMentorAdminVosByChallengeId(Long challengeId);
}
