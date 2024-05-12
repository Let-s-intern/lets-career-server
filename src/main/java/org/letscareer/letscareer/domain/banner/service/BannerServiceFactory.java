package org.letscareer.letscareer.domain.banner.service;

import org.letscareer.letscareer.domain.banner.type.BannerType;

public interface BannerServiceFactory {
    BannerService getBannerService(BannerType bannerType);
}
