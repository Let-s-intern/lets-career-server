package org.letscareer.letscareer.domain.blogbanner.service;

import org.letscareer.letscareer.domain.blogbanner.dto.request.CreateBlogBannerRequestDto;

public interface BlogBannerService {
    void createBlogBanner(CreateBlogBannerRequestDto requestDto);
}
