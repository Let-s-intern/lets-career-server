package org.letscareer.letscareer.domain.banner.mapper;

import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.entity.MainBanner;
import org.letscareer.letscareer.domain.banner.type.BannerType;
import org.letscareer.letscareer.domain.file.entity.File;
import org.springframework.stereotype.Component;

@Component
public class MainBannerMapper {
    public MainBanner toEntity(BannerType type, CreateBannerRequestDto createBannerRequestDto, File file) {
        return MainBanner.createMainBanner(type, createBannerRequestDto, file);
    }
}
