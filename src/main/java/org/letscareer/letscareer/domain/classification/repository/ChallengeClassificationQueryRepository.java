package org.letscareer.letscareer.domain.classification.repository;

import org.letscareer.letscareer.domain.classification.vo.ChallengeClassificationDetailVo;

import java.util.List;

public interface ChallengeClassificationQueryRepository {
    List<ChallengeClassificationDetailVo> findClassificationDetailVos(Long challengeId);
}
