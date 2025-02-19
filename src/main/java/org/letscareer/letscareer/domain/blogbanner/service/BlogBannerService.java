package org.letscareer.letscareer.domain.blogbanner.service;

import org.letscareer.letscareer.domain.blogbanner.dto.request.CreateBlogBannerRequestDto;
import org.letscareer.letscareer.domain.blogbanner.dto.request.UpdateBlogBannerRequestDto;
import org.letscareer.letscareer.domain.blogbanner.dto.response.GetAdminBlogBannerResponseDto;

public interface BlogBannerService {
    GetAdminBlogBannerResponseDto getAdminBlogBanners();
    void createBlogBanner(CreateBlogBannerRequestDto requestDto);
    void updateBlogBanner(Long blogBannerId, UpdateBlogBannerRequestDto requestDto);
    void deleteBlogBanner(Long blogBannerId);
}
