package org.letscareer.letscareer.domain.banner.mapper;

import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.entity.MainBottomBanner;
import org.letscareer.letscareer.domain.banner.type.BannerType;
import org.letscareer.letscareer.domain.file.entity.File;
import org.springframework.stereotype.Component;

@Component
public class MainBottomBannerMapper {
    public MainBottomBanner toEntity(BannerType type, CreateBannerRequestDto createBannerRequestDto, File file, File newMobileFile) {
        return MainBottomBanner.createMainBottomBanner(type, createBannerRequestDto, file, newMobileFile);
    }
}
