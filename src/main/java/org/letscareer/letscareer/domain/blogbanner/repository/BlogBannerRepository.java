package org.letscareer.letscareer.domain.blogbanner.repository;

import org.letscareer.letscareer.domain.blogbanner.entity.BlogBanner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogBannerRepository extends JpaRepository<BlogBanner, Long>,  BlogBannerQueryRepository {
}
