package org.letscareer.letscareer.domain.blogbanner.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.blogbanner.vo.AdminBlogBannerDetailVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetAdminBlogBannerResponseDto(
        AdminBlogBannerDetailVo blogBannerInfo
) {
    public static GetAdminBlogBannerResponseDto of(AdminBlogBannerDetailVo adminBlogBannerDetailVo){
        return GetAdminBlogBannerResponseDto.builder()
                .blogBannerInfo(adminBlogBannerDetailVo)
                .build();
    }
}
