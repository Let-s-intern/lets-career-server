package org.letscareer.letscareer.domain.blogbanner.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.blogbanner.vo.BlogBannerVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetBlogBannersResponseDto(
        List<BlogBannerVo> blogBannerList
) {
    public static GetBlogBannersResponseDto of(List<BlogBannerVo> blogBannerList){
        return GetBlogBannersResponseDto.builder()
                .blogBannerList(blogBannerList)
                .build();
    }
}
