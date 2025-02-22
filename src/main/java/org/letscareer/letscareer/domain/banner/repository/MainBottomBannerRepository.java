package org.letscareer.letscareer.domain.banner.repository;

import org.letscareer.letscareer.domain.banner.entity.MainBottomBanner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainBottomBannerRepository extends JpaRepository<MainBottomBanner, Long>, MainBottomBannerQueryRepository {
}
