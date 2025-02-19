package org.letscareer.letscareer.domain.blogbanner.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blogbanner.dto.request.CreateBlogBannerRequestDto;
import org.letscareer.letscareer.domain.blogbanner.helper.BlogBannerHelper;
import org.letscareer.letscareer.domain.blogbanner.mapper.BlogBannerMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class BlogBannerServiceImpl implements BlogBannerService{
    private final BlogBannerHelper blogBannerHelper;
    private final BlogBannerMapper blogBannerMapper;

    @Override
    public void createBlogBanner(CreateBlogBannerRequestDto requestDto){
        blogBannerHelper.createBlogBannerAndSave(requestDto);
    }
}
