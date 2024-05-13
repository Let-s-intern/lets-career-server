package org.letscareer.letscareer.domain.challenge.repository;

import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationVo;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeDetailVo;

import java.util.List;
import java.util.Optional;

public interface ChallengeQueryRepository {
    Optional<ChallengeDetailVo> findChallengeDetailById(Long challengeId);
}
