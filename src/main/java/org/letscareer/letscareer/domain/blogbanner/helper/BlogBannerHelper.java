package org.letscareer.letscareer.domain.blogbanner.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;import org.letscareer.letscareer.domain.blogbanner.dto.request.CreateBlogBannerRequestDto;
import org.letscareer.letscareer.domain.blogbanner.entity.BlogBanner;
import org.letscareer.letscareer.domain.blogbanner.repository.BlogBannerRepository;
import org.letscareer.letscareer.domain.blogbanner.vo.AdminBlogBannerDetailVo;
import org.letscareer.letscareer.domain.blogbanner.vo.AdminBlogBannerVo;
import org.letscareer.letscareer.domain.blogbanner.vo.BlogBannerVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.letscareer.letscareer.domain.blogbanner.error.BlogBannerErrorCode.BLOG_BANNER_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class BlogBannerHelper {
    private final BlogBannerRepository blogBannerRepository;

    public Page<BlogBannerVo> findBlogBannerVos(Pageable pageable) {
        return blogBannerRepository.findBlogBannerVos(pageable);
    }

    public List<AdminBlogBannerVo> findAdminBlogBannerVos(){
        return blogBannerRepository.findAdminBlogBannerVos();
    }

    public AdminBlogBannerDetailVo findAdminBlogBannerDetailVoByIdOrThrow(Long blogBannerId){
        return blogBannerRepository.findAdminBlogBannerDetailVoById(blogBannerId).orElseThrow(() -> new EntityNotFoundException(BLOG_BANNER_NOT_FOUND));
    }
    public void createBlogBannerAndSave(CreateBlogBannerRequestDto requestDto){
        BlogBanner blogBanner = BlogBanner.createBlogBanner(requestDto);
        blogBannerRepository.save(blogBanner);
    }

    public BlogBanner findBlogBannerByIdOrThrow(Long blogBannerId){
        return blogBannerRepository.findById(blogBannerId).orElseThrow(() -> new EntityNotFoundException(BLOG_BANNER_NOT_FOUND));
    }

    public void deleteBlogBanner(BlogBanner blogBanner){
        blogBannerRepository.delete(blogBanner);
    }
}
