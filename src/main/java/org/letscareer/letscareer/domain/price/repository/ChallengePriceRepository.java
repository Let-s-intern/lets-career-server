package org.letscareer.letscareer.domain.price.repository;

import org.letscareer.letscareer.domain.price.entity.ChallengePrice;
import org.letscareer.letscareer.domain.price.type.ChallengePricePlanType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChallengePriceRepository extends JpaRepository<ChallengePrice, Long>, ChallengePriceQueryRepository {
    void deleteAllByChallengeId(Long challengeId);
    Optional<ChallengePrice> findByChallengeIdAndChallengePricePlanType(Long challengeId, ChallengePricePlanType challengePricePlanType);
}
