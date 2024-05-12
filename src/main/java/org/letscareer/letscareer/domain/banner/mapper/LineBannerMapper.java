package org.letscareer.letscareer.domain.banner.mapper;

import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.entity.LineBanner;
import org.letscareer.letscareer.domain.banner.type.BannerType;
import org.springframework.stereotype.Component;

@Component
public class LineBannerMapper {
    public LineBanner toEntity(BannerType type, CreateBannerRequestDto createBannerRequestDto) {
        return LineBanner.createLineBanner(type, createBannerRequestDto);
    }
}
