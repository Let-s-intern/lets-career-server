package org.letscareer.letscareer.domain.price.repository;

import org.letscareer.letscareer.domain.price.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long>, PriceQueryRepository {
}
