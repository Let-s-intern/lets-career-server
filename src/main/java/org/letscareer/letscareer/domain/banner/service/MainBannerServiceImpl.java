package org.letscareer.letscareer.domain.banner.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.request.UpdateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.response.BannerAdminListResponseDto;
import org.letscareer.letscareer.domain.banner.entity.MainBanner;
import org.letscareer.letscareer.domain.banner.helper.MainBannerHelper;
import org.letscareer.letscareer.domain.banner.mapper.BannerMapper;
import org.letscareer.letscareer.domain.banner.mapper.MainBannerMapper;
import org.letscareer.letscareer.domain.banner.type.BannerType;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service("MAIN")
public class MainBannerServiceImpl implements BannerService {
    private final MainBannerHelper mainBannerHelper;
    private final MainBannerMapper mainBannerMapper;
    private final BannerMapper bannerMapper;

    @Override
    public void createBanner(BannerType type, CreateBannerRequestDto createBannerRequestDto) {
        MainBanner newMainBanner = mainBannerMapper.toEntity(type, createBannerRequestDto);
        mainBannerHelper.saveMainBanner(newMainBanner);
    }

    @Override
    public BannerAdminListResponseDto getBannersForAdmin() {
        List<BannerAdminVo> mainBannerAdminList = mainBannerHelper.findAllMainBannerAdminVos();
        return bannerMapper.toBannerAdminListResponseDto(mainBannerAdminList);
    }

    @Override
    public void updateBanner(Long bannerId, UpdateBannerRequestDto updateBannerRequestDto) {
        MainBanner mainBanner = mainBannerHelper.findByIdOrThrow(bannerId);
        mainBanner.updateMainBanner(updateBannerRequestDto);
    }

    @Override
    public void deleteBanner(Long bannerId) {
        MainBanner mainBanner = mainBannerHelper.findByIdOrThrow(bannerId);
        mainBannerHelper.deleteMainBanner(mainBanner);
    }

}
