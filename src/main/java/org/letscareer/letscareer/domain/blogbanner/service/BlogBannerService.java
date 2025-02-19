package org.letscareer.letscareer.domain.blogbanner.service;

import org.letscareer.letscareer.domain.blogbanner.dto.request.CreateBlogBannerRequestDto;
import org.letscareer.letscareer.domain.blogbanner.dto.request.UpdateBlogBannerRequestDto;
import org.letscareer.letscareer.domain.blogbanner.dto.response.GetAdminBlogBannerResponseDto;
import org.letscareer.letscareer.domain.blogbanner.dto.response.GetAdminBlogBannersResponseDto;

public interface BlogBannerService {
    GetAdminBlogBannersResponseDto getAdminBlogBanners();
    GetAdminBlogBannerResponseDto getAdminBlogBanner(Long blogBannerId);
    void createBlogBanner(CreateBlogBannerRequestDto requestDto);
    void updateBlogBanner(Long blogBannerId, UpdateBlogBannerRequestDto requestDto);
    void deleteBlogBanner(Long blogBannerId);
}
