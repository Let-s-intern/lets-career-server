package org.letscareer.letscareer.domain.banner.repository;

import org.letscareer.letscareer.domain.banner.vo.LineBannerAdminVo;
import org.letscareer.letscareer.domain.banner.vo.LineBannerUserVo;

import java.util.List;

public interface LineBannerQueryRepository {
    List<LineBannerAdminVo> findAllLineBannerAdminVos();

    List<LineBannerUserVo> findAllLineBannerUserVos();
}
