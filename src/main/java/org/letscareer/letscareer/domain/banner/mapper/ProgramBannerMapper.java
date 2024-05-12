package org.letscareer.letscareer.domain.banner.mapper;

import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.entity.ProgramBanner;
import org.letscareer.letscareer.domain.banner.type.BannerType;
import org.springframework.stereotype.Component;

@Component
public class ProgramBannerMapper {
    public ProgramBanner toEntity(BannerType type, CreateBannerRequestDto createBannerRequestDto) {
        return ProgramBanner.createProgramBanner(type, createBannerRequestDto);
    }
}
