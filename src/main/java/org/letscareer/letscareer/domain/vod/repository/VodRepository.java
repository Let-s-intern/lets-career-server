package org.letscareer.letscareer.domain.vod.repository;

import org.letscareer.letscareer.domain.vod.entity.Vod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VodRepository extends JpaRepository<Vod, Long>, VodQueryRepository {
}
