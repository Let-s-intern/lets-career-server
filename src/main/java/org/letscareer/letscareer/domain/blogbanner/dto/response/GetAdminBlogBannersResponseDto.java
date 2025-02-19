package org.letscareer.letscareer.domain.blogbanner.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.blogbanner.vo.AdminBlogBannerVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetAdminBlogBannersResponseDto(
        List<AdminBlogBannerVo> blogBannerList
) {
    public static GetAdminBlogBannersResponseDto of(List<AdminBlogBannerVo> blogBannerList) {
        return GetAdminBlogBannersResponseDto.builder()
                .blogBannerList(blogBannerList)
                .build();
    }
}
