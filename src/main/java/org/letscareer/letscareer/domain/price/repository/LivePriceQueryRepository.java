package org.letscareer.letscareer.domain.price.repository;

import org.letscareer.letscareer.domain.price.vo.LivePriceDetailVo;
import org.letscareer.letscareer.domain.price.vo.PriceDetailVo;

import java.util.Optional;

public interface LivePriceQueryRepository {
    Optional<LivePriceDetailVo> findLivePriceDetailVo(Long liveId);

    Optional<PriceDetailVo> findPriceDetailVoByLiveId(Long programId);
}
