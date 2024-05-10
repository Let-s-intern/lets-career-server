package org.letscareer.letscareer.domain.price.repository;

import org.letscareer.letscareer.domain.price.entity.ChallengePrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengePriceRepository extends JpaRepository<ChallengePrice, Long>, ChallengePriceQueryRepository {
}
