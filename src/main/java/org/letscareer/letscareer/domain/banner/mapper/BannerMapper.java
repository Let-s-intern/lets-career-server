package org.letscareer.letscareer.domain.banner.mapper;

import org.letscareer.letscareer.domain.banner.dto.response.BannerAdminListResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BannerMapper {
    public BannerAdminListResponseDto toBannerAdminListResponseDto(List<?> bannerAdminList) {
        return BannerAdminListResponseDto.of(bannerAdminList);
    }
}
