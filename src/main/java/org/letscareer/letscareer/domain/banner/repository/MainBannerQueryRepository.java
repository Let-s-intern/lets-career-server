package org.letscareer.letscareer.domain.banner.repository;

import org.letscareer.letscareer.domain.banner.vo.BannerAdminVo;
import org.letscareer.letscareer.domain.banner.vo.BannerUserVo;

import java.util.List;

public interface MainBannerQueryRepository {
    List<BannerAdminVo> findAllMainBannerAdminVos();

    List<BannerUserVo> findAllUserBannerAdminVos();
}
