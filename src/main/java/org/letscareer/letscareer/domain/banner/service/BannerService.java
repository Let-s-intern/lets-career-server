package org.letscareer.letscareer.domain.banner.service;

import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.type.BannerType;

public interface BannerService {
    void createBanner(BannerType type, CreateBannerRequestDto createBannerRequestDto);
}
