package org.letscareer.letscareer.domain.blogbanner.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blogbanner.dto.request.CreateBlogBannerRequestDto;
import org.letscareer.letscareer.domain.blogbanner.entity.BlogBanner;
import org.letscareer.letscareer.domain.blogbanner.repository.BlogBannerRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BlogBannerHelper {
    private final BlogBannerRepository blogBannerRepository;

    public void createBlogBannerAndSave(CreateBlogBannerRequestDto requestDto){
        BlogBanner blogBanner = BlogBanner.createBlogBanner(requestDto);
        blogBannerRepository.save(blogBanner);
    }
}
