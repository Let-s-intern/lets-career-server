package org.letscareer.letscareer.domain.banner.repository;

import org.letscareer.letscareer.domain.banner.vo.PopupBannerAdminDetailVo;
import org.letscareer.letscareer.domain.banner.vo.PopupBannerAdminVo;
import org.letscareer.letscareer.domain.banner.vo.PopupUserVo;

import java.util.List;
import java.util.Optional;

public interface PopupQueryRepository {
    List<PopupBannerAdminVo> findAllPopupAdminVos();

    Optional<PopupBannerAdminDetailVo> findBannerAdminDetailVo(Long bannerId);

    List<PopupUserVo> findAllPopBannerUserVos();
}
