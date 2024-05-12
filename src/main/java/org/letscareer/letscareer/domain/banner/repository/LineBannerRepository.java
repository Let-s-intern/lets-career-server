package org.letscareer.letscareer.domain.banner.repository;

import org.letscareer.letscareer.domain.banner.entity.LineBanner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineBannerRepository extends JpaRepository<LineBanner, Long>, LineBannerQueryRepository {
}
