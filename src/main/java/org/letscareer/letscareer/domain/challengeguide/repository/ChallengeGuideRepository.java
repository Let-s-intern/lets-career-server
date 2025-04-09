package org.letscareer.letscareer.domain.challengeguide.repository;

import org.letscareer.letscareer.domain.challengeguide.entity.ChallengeGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ChallengeGuideRepository extends JpaRepository<ChallengeGuide, Long>, ChallengeGuideQueryRepository {
    @Modifying
    @Query("DELETE FROM ChallengeGuide as cg WHERE cg.challenge.id = :challengeId")
    void deleteAllByChallengeId(Long challengeId);
}
