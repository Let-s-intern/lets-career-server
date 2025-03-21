package org.letscareer.letscareer.domain.blogbanner.mapper;

import org.springframework.data.domain.Page;
import org.letscareer.letscareer.domain.blogbanner.dto.response.GetAdminBlogBannerResponseDto;
import org.letscareer.letscareer.domain.blogbanner.dto.response.GetAdminBlogBannersResponseDto;
import org.letscareer.letscareer.domain.blogbanner.dto.response.GetBlogBannersResponseDto;
import org.letscareer.letscareer.domain.blogbanner.vo.AdminBlogBannerDetailVo;
import org.letscareer.letscareer.domain.blogbanner.vo.AdminBlogBannerVo;
import org.letscareer.letscareer.domain.blogbanner.vo.BlogBannerVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlogBannerMapper {
    public GetBlogBannersResponseDto toGetBlogBannersResponseDto(Page<BlogBannerVo> blogBannerVoList){
        return GetBlogBannersResponseDto.of(blogBannerVoList);
    }

    public GetAdminBlogBannersResponseDto toGetAdminBlogBannersResponseDto(List<AdminBlogBannerVo> adminBlogBannerVoList){
        return GetAdminBlogBannersResponseDto.of(adminBlogBannerVoList);
    }

    public GetAdminBlogBannerResponseDto toGetAdminBlogBannerResponseDto(AdminBlogBannerDetailVo adminBlogBannerDetailVo){
        return GetAdminBlogBannerResponseDto.of(adminBlogBannerDetailVo);
    }
}
