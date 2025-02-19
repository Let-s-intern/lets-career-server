package org.letscareer.letscareer.domain.blogbanner.service;

import org.letscareer.letscareer.domain.blogbanner.dto.request.CreateBlogBannerRequestDto;
import org.letscareer.letscareer.domain.blogbanner.dto.request.UpdateBlogBannerRequestDto;

public interface BlogBannerService {
    void createBlogBanner(CreateBlogBannerRequestDto requestDto);
    void updateBlogBanner(Long blogBannerId, UpdateBlogBannerRequestDto requestDto);
}
