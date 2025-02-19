package org.letscareer.letscareer.domain.blogbanner.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.blogbanner.vo.AdminBlogBannerVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetAdminBlogBannerResponseDto(
        List<AdminBlogBannerVo> blogBannerList
) {
    public static GetAdminBlogBannerResponseDto of(List<AdminBlogBannerVo> blogBannerList) {
        return GetAdminBlogBannerResponseDto.builder()
                .blogBannerList(blogBannerList)
                .build();
    }
}
