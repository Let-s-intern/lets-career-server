package org.letscareer.letscareer.domain.blogbanner.service;

import org.letscareer.letscareer.domain.blogbanner.dto.request.CreateBlogBannerRequestDto;
import org.letscareer.letscareer.domain.blogbanner.dto.request.UpdateBlogBannerRequestDto;
import org.letscareer.letscareer.domain.blogbanner.dto.response.GetAdminBlogBannerResponseDto;
import org.letscareer.letscareer.domain.blogbanner.dto.response.GetAdminBlogBannersResponseDto;
import org.letscareer.letscareer.domain.blogbanner.dto.response.GetBlogBannersResponseDto;
import org.springframework.data.domain.Pageable;

public interface BlogBannerService {

    GetBlogBannersResponseDto getBlogBanners(Pageable pageable);

    GetAdminBlogBannersResponseDto getAdminBlogBanners();
    GetAdminBlogBannerResponseDto getAdminBlogBanner(Long blogBannerId);
    void createBlogBanner(CreateBlogBannerRequestDto requestDto);
    void updateBlogBanner(Long blogBannerId, UpdateBlogBannerRequestDto requestDto);
    void deleteBlogBanner(Long blogBannerId);
}
