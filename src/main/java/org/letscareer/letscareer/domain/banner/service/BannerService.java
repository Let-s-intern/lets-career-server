package org.letscareer.letscareer.domain.banner.service;

import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.request.UpdateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.response.BannerDetailResponseDto;
import org.letscareer.letscareer.domain.banner.dto.response.BannerListResponseDto;
import org.letscareer.letscareer.domain.banner.type.BannerType;

public interface BannerService {
    BannerListResponseDto getBanners();

    BannerListResponseDto getBannersForAdmin();

    BannerDetailResponseDto getBannerDetail(Long bannerId);

    void createBanner(BannerType type, CreateBannerRequestDto createBannerRequestDto);

    void updateBanner(Long bannerId, UpdateBannerRequestDto updateBannerRequestDto);

    void deleteBanner(Long bannerId);
}
