package org.letscareer.letscareer.domain.banner.repository;

import org.letscareer.letscareer.domain.banner.vo.BannerAdminDetailVo;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminVo;
import org.letscareer.letscareer.domain.banner.vo.BannerUserVo;

import java.util.List;
import java.util.Optional;

public interface MainBannerQueryRepository {
    List<BannerAdminVo> findAllMainBannerAdminVos();

    List<BannerUserVo> findAllMainBannerUserVos();

    Optional<BannerAdminDetailVo> findBannerAdminDetailVo(Long bannerId);
}
