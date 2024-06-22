package org.letscareer.letscareer.domain.challengeguide.repository;

import org.letscareer.letscareer.domain.challengeguide.vo.ChallengeGuideVo;

import java.util.List;

public interface ChallengeGuideQueryRepository {
    List<ChallengeGuideVo> findAllChallengeGuideAdminVos(Long challengeId);
}
