package org.letscareer.letscareer.domain.admincalssification.repository;

import org.letscareer.letscareer.domain.admincalssification.vo.ChallengeAdminClassificationDetailVo;

import java.util.List;

public interface ChallengeAdminClassificationQueryRepository {
    List<ChallengeAdminClassificationDetailVo> findClassificationDetailVos(Long challengeId);
}
