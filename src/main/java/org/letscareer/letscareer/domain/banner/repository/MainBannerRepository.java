package org.letscareer.letscareer.domain.banner.repository;

import org.letscareer.letscareer.domain.banner.entity.MainBanner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainBannerRepository extends JpaRepository<MainBanner, Long>, MainBannerQueryRepository {
}
