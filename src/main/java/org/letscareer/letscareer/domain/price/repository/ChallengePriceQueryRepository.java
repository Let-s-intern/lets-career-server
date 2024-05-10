package org.letscareer.letscareer.domain.price.repository;

import org.letscareer.letscareer.domain.price.vo.ChallengePriceDetailVo;

import java.util.List;

public interface ChallengePriceQueryRepository {
    List<ChallengePriceDetailVo> findChallengePriceDetailVos(Long challengeId);
}
