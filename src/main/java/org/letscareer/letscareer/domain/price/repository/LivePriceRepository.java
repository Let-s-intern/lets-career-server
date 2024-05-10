package org.letscareer.letscareer.domain.price.repository;

import org.letscareer.letscareer.domain.price.entity.LivePrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivePriceRepository extends JpaRepository<LivePrice, Long>, LivePriceQueryRepository {
}
