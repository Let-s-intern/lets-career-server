package org.letscareer.letscareer.domain.challengeoption.repository;

import org.letscareer.letscareer.domain.challengeoption.entity.ChallengePriceOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengePriceOptionRepository extends JpaRepository<ChallengePriceOption, Long>, ChallengePriceOptionQueryRepository {
}
