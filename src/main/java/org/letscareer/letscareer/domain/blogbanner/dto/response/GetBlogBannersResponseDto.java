package org.letscareer.letscareer.domain.blogbanner.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.springframework.data.domain.Page;
import org.letscareer.letscareer.domain.blogbanner.vo.BlogBannerVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetBlogBannersResponseDto(
        List<BlogBannerVo> blogBannerList,
        PageInfo pageInfo
) {
    public static GetBlogBannersResponseDto of(Page<BlogBannerVo> blogBannerList){
        PageInfo pageInfo = PageInfo.of(blogBannerList);
        return GetBlogBannersResponseDto.builder()
                .blogBannerList(blogBannerList.getContent())
                .pageInfo(pageInfo)
                .build();
    }
}
