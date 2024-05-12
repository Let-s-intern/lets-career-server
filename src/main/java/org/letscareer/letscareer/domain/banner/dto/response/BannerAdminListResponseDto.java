package org.letscareer.letscareer.domain.banner.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record BannerAdminListResponseDto(
        List<?> bannerAdminList
) {
    public static <T> BannerAdminListResponseDto of(List<T> bannerAdminList) {
        return BannerAdminListResponseDto.builder()
                .bannerAdminList(bannerAdminList)
                .build();
    }
}
