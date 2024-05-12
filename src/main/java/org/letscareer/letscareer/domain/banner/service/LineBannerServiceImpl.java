package org.letscareer.letscareer.domain.banner.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.entity.LineBanner;
import org.letscareer.letscareer.domain.banner.helper.LineBannerHelper;
import org.letscareer.letscareer.domain.banner.mapper.LineBannerMapper;
import org.letscareer.letscareer.domain.banner.type.BannerType;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("LINE")
public class LineBannerServiceImpl implements BannerService {
    private final LineBannerHelper lineBannerHelper;
    private final LineBannerMapper lineBannerMapper;

    @Override
    public void createBanner(BannerType type, CreateBannerRequestDto createBannerRequestDto) {
        LineBanner newLineBanner = lineBannerMapper.toEntity(type, createBannerRequestDto);
        lineBannerHelper.saveLineBanner(newLineBanner);
    }
}
