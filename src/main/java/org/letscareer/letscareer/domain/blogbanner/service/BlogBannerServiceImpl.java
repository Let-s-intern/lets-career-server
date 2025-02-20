package org.letscareer.letscareer.domain.blogbanner.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blogbanner.dto.request.CreateBlogBannerRequestDto;
import org.letscareer.letscareer.domain.blogbanner.dto.request.UpdateBlogBannerRequestDto;
import org.letscareer.letscareer.domain.blogbanner.dto.response.GetAdminBlogBannerResponseDto;
import org.letscareer.letscareer.domain.blogbanner.dto.response.GetAdminBlogBannersResponseDto;
import org.letscareer.letscareer.domain.blogbanner.dto.response.GetBlogBannersResponseDto;
import org.letscareer.letscareer.domain.blogbanner.entity.BlogBanner;
import org.letscareer.letscareer.domain.blogbanner.helper.BlogBannerHelper;
import org.letscareer.letscareer.domain.blogbanner.mapper.BlogBannerMapper;
import org.letscareer.letscareer.domain.blogbanner.vo.AdminBlogBannerDetailVo;
import org.letscareer.letscareer.domain.blogbanner.vo.AdminBlogBannerVo;
import org.letscareer.letscareer.domain.blogbanner.vo.BlogBannerVo;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class BlogBannerServiceImpl implements BlogBannerService{
    private final BlogBannerHelper blogBannerHelper;
    private final BlogBannerMapper blogBannerMapper;

    @Override
    public GetBlogBannersResponseDto getBlogBanners() {
        List<BlogBannerVo> blogBannerVoList = blogBannerHelper.findBlogBannerVos();
        return blogBannerMapper.toGetBlogBannersResponseDto(blogBannerVoList);
    }

    @Override
    public GetAdminBlogBannersResponseDto getAdminBlogBanners(){
        List<AdminBlogBannerVo> adminBlogBannerVoList = blogBannerHelper.findAdminBlogBannerVos();
        return blogBannerMapper.toGetAdminBlogBannersResponseDto(adminBlogBannerVoList);
    }

    @Override
    public GetAdminBlogBannerResponseDto getAdminBlogBanner(Long blogBannerId){
        AdminBlogBannerDetailVo adminBlogBannerDetailVo = blogBannerHelper.findAdminBlogBannerDetailVoByIdOrThrow(blogBannerId);
        return blogBannerMapper.toGetAdminBlogBannerResponseDto(adminBlogBannerDetailVo);
    }

    @Override
    public void createBlogBanner(CreateBlogBannerRequestDto requestDto){
        blogBannerHelper.createBlogBannerAndSave(requestDto);
    }

    @Override
    public void updateBlogBanner(Long blogBannerId, UpdateBlogBannerRequestDto requestDto){
        BlogBanner blogBanner = blogBannerHelper.findBlogBannerByIdOrThrow(blogBannerId);
        blogBanner.updateBlogBanner(requestDto);
    }

    @Override
    public void deleteBlogBanner(Long blogBannerId){
        BlogBanner blogBanner = blogBannerHelper.findBlogBannerByIdOrThrow(blogBannerId);
        blogBannerHelper.deleteBlogBanner(blogBanner);
    }
}
