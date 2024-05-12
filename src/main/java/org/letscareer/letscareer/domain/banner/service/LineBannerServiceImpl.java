package org.letscareer.letscareer.domain.banner.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.response.BannerAdminListResponseDto;
import org.letscareer.letscareer.domain.banner.entity.LineBanner;
import org.letscareer.letscareer.domain.banner.helper.LineBannerHelper;
import org.letscareer.letscareer.domain.banner.mapper.BannerMapper;
import org.letscareer.letscareer.domain.banner.mapper.LineBannerMapper;
import org.letscareer.letscareer.domain.banner.type.BannerType;
import org.letscareer.letscareer.domain.banner.vo.LineBannerAdminVo;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("LINE")
public class LineBannerServiceImpl implements BannerService {
    private final LineBannerHelper lineBannerHelper;
    private final LineBannerMapper lineBannerMapper;
    private final BannerMapper bannerMapper;

    @Override
    public void createBanner(BannerType type, CreateBannerRequestDto createBannerRequestDto) {
        LineBanner newLineBanner = lineBannerMapper.toEntity(type, createBannerRequestDto);
        lineBannerHelper.saveLineBanner(newLineBanner);
    }

    @Override
    public BannerAdminListResponseDto getBannersForAdmin() {
        List<LineBannerAdminVo> lineBannerAdminList = lineBannerHelper.findAllLineBannerAdminVos();
        return bannerMapper.toBannerAdminListResponseDto(lineBannerAdminList);
    }
}
