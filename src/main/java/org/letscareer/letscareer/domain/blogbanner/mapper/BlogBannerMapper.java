package org.letscareer.letscareer.domain.blogbanner.mapper;

import org.letscareer.letscareer.domain.blogbanner.dto.response.GetAdminBlogBannersResponseDto;
import org.letscareer.letscareer.domain.blogbanner.vo.AdminBlogBannerVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlogBannerMapper {
    public GetAdminBlogBannersResponseDto toGetAdminBlogBannersResponseDto(List<AdminBlogBannerVo> adminBlogBannerVoList){
        return GetAdminBlogBannersResponseDto.of(adminBlogBannerVoList);
    }
}
