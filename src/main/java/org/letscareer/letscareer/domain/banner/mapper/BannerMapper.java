package org.letscareer.letscareer.domain.banner.mapper;

import org.letscareer.letscareer.domain.banner.dto.response.BannerDetailResponseDto;
import org.letscareer.letscareer.domain.banner.dto.response.BannerListResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BannerMapper {
    public BannerListResponseDto toBannerAdminListResponseDto(List<?> bannerAdminList) {
        return BannerListResponseDto.of(bannerAdminList);
    }

    public <T> BannerDetailResponseDto toBannerDetailResponseDto(T bannerAdminDetailVo) {
        return BannerDetailResponseDto.of(bannerAdminDetailVo);
    }
}
