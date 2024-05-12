package org.letscareer.letscareer.domain.challengeguide.repository;

import org.letscareer.letscareer.domain.challengeguide.entity.ChallengeGuide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeGuideRepository extends JpaRepository<ChallengeGuide, Long>, ChallengeGuideQueryRepository {
}
