package org.letscareer.letscareer.domain.banner.repository;

import org.letscareer.letscareer.domain.banner.vo.LineBannerAdminDetailVo;
import org.letscareer.letscareer.domain.banner.vo.LineBannerAdminVo;
import org.letscareer.letscareer.domain.banner.vo.LineBannerUserVo;

import java.util.List;
import java.util.Optional;

public interface LineBannerQueryRepository {
    List<LineBannerAdminVo> findAllLineBannerAdminVos();

    List<LineBannerUserVo> findAllLineBannerUserVos();

    Optional<LineBannerAdminDetailVo> findLineBannerAdminDetailVo(Long bannerId);
}
