package org.letscareer.letscareer.domain.challenge.repository;

import org.letscareer.letscareer.domain.challenge.vo.ChallengeDetailVo;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeProfileVo;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ChallengeQueryRepository {
    Optional<ChallengeDetailVo> findChallengeDetailById(Long challengeId);

    Page<ChallengeProfileVo> findChallengeProfiles(ProgramClassification type, Pageable pageable);
}
