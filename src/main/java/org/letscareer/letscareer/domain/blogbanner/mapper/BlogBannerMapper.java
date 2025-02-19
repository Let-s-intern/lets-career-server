package org.letscareer.letscareer.domain.blogbanner.mapper;

import org.letscareer.letscareer.domain.blogbanner.dto.response.GetAdminBlogBannerResponseDto;
import org.letscareer.letscareer.domain.blogbanner.vo.AdminBlogBannerVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlogBannerMapper {
    public GetAdminBlogBannerResponseDto toGetAdminBlogBannersResponseDto(List<AdminBlogBannerVo> adminBlogBannerVoList){
        return GetAdminBlogBannerResponseDto.of(adminBlogBannerVoList);
    }
}
