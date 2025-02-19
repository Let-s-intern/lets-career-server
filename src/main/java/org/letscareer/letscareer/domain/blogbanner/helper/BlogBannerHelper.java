package org.letscareer.letscareer.domain.blogbanner.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blogbanner.dto.request.CreateBlogBannerRequestDto;
import org.letscareer.letscareer.domain.blogbanner.entity.BlogBanner;
import org.letscareer.letscareer.domain.blogbanner.repository.BlogBannerRepository;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import static org.letscareer.letscareer.domain.blogbanner.error.BlogBannerErrorCode.BLOG_BANNER_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class BlogBannerHelper {
    private final BlogBannerRepository blogBannerRepository;

    public void createBlogBannerAndSave(CreateBlogBannerRequestDto requestDto){
        BlogBanner blogBanner = BlogBanner.createBlogBanner(requestDto);
        blogBannerRepository.save(blogBanner);
    }

    public BlogBanner findBlogBannerByIdOrThrow(Long blogBannerId){
        return blogBannerRepository.findById(blogBannerId).orElseThrow(() -> new EntityNotFoundException(BLOG_BANNER_NOT_FOUND));
    }
}
