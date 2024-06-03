package org.letscareer.letscareer.domain.challenge.repository;

import org.letscareer.letscareer.domain.challenge.vo.*;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ChallengeQueryRepository {
    Optional<ChallengeDetailVo> findChallengeDetailById(Long challengeId);

    Page<ChallengeProfileVo> findChallengeProfiles(List<ProgramClassification> typeList, List<ProgramStatusType> statusList, Pageable pageable);

    Optional<ChallengeTitleVo> findChallengeTitleVo(Long challengeId);

    Optional<ChallengeThumbnailVo> findChallengeThumbnailVo(Long challengeId);

    Optional<ChallengeContentVo> findChallengeContentVo(Long challengeId);

    Optional<ChallengeApplicationFormVo> findChallengeApplicationFormVo(Long challengeId);
}
