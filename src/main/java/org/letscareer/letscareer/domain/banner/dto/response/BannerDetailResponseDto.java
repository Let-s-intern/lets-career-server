package org.letscareer.letscareer.domain.banner.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record BannerDetailResponseDto<T>(
        T bannerAdminDetailVo
) {
    public static <T> BannerDetailResponseDto of(T bannerAdminDetailVo) {
        return BannerDetailResponseDto.builder()
                .bannerAdminDetailVo(bannerAdminDetailVo)
                .build();
    }
}
