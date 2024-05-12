package org.letscareer.letscareer.domain.challengeguide.repository;

import org.letscareer.letscareer.domain.challengeguide.vo.ChallengeGuideAdminVo;

import java.util.List;

public interface ChallengeGuideQueryRepository {
    List<ChallengeGuideAdminVo> findAllChallengeGuideAdminVos(Long challengeId);
}
