package org.letscareer.letscareer.domain.live.repository;

import org.letscareer.letscareer.domain.live.entity.Live;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiveRepository extends JpaRepository<Live, Long>, LiveQueryRepository {
}
