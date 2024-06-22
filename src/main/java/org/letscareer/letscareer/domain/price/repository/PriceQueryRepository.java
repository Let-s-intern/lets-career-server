package org.letscareer.letscareer.domain.price.repository;

import org.letscareer.letscareer.domain.price.vo.PriceDetailVo;

import java.util.Optional;

public interface PriceQueryRepository {
    Optional<PriceDetailVo> findPriceDetailVoByPriceId(Long priceId);
}
