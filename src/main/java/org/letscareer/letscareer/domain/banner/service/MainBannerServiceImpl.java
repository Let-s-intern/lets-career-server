package org.letscareer.letscareer.domain.banner.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.response.BannerAdminListResponseDto;
import org.letscareer.letscareer.domain.banner.entity.MainBanner;
import org.letscareer.letscareer.domain.banner.helper.MainBannerHelper;
import org.letscareer.letscareer.domain.banner.mapper.BannerMapper;
import org.letscareer.letscareer.domain.banner.mapper.MainBannerMapper;
import org.letscareer.letscareer.domain.banner.type.BannerType;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminVo;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
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
}
