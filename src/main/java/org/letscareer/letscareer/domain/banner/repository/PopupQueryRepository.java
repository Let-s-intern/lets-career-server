package org.letscareer.letscareer.domain.banner.repository;

import org.letscareer.letscareer.domain.banner.vo.BannerAdminVo;

import java.util.List;

public interface PopupQueryRepository {
    List<BannerAdminVo> findAllPopupAdminVos();
}
