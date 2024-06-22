package org.letscareer.letscareer.domain.banner.repository;

import org.letscareer.letscareer.domain.banner.vo.BannerAdminDetailVo;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminVo;
import org.letscareer.letscareer.domain.banner.vo.BannerUserVo;

import java.util.List;
import java.util.Optional;

public interface ProgramBannerQueryRepository {
    List<BannerAdminVo> findAllProgramBannerAdminVos();

    Optional<BannerAdminDetailVo> findBannerAdminDetailVo(Long bannerId);

    List<BannerUserVo> findAllProgramBannerUserVos();
}
