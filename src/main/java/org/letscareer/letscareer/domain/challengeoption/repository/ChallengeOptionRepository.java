package org.letscareer.letscareer.domain.challengeoption.repository;

import org.letscareer.letscareer.domain.challengeoption.entity.ChallengeOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeOptionRepository extends JpaRepository<ChallengeOption, Long>, ChallengeOptionQueryRepository {
}
