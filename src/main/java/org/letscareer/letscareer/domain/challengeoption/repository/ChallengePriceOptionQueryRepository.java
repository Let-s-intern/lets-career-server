package org.letscareer.letscareer.domain.challengeoption.repository;

import java.util.List;

public interface ChallengePriceOptionQueryRepository {
    List<String> findAllByChallengePriceId(Long challengePriceId);
}
