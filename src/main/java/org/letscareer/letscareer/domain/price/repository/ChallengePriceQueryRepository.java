package org.letscareer.letscareer.domain.price.repository;

import org.letscareer.letscareer.domain.price.entity.ChallengePrice;
import org.letscareer.letscareer.domain.price.vo.ChallengePriceDetailVo;
import org.letscareer.letscareer.domain.price.vo.PriceDetailVo;

import java.util.List;
import java.util.Optional;

public interface ChallengePriceQueryRepository {
    List<ChallengePriceDetailVo> findChallengePriceDetailVos(Long challengeId);

    Optional<PriceDetailVo> findPriceDetailVoByChallengeId(Long programId, Long applicationId);

    Optional<Integer> findPriceRefundByChallengeId(Long challengeId);

    Optional<ChallengePrice> findByPriceId(Long priceId);
}
