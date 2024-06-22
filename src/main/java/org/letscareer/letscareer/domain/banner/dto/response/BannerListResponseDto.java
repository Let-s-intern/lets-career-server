package org.letscareer.letscareer.domain.banner.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record BannerListResponseDto(
        List<?> bannerList
) {
    public static <T> BannerListResponseDto of(List<T> bannerList) {
        return BannerListResponseDto.builder()
                .bannerList(bannerList)
                .build();
    }
}
