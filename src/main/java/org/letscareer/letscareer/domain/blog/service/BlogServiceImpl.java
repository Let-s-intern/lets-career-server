package org.letscareer.letscareer.domain.blog.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.dto.request.CreateBlogRequestDto;
import org.letscareer.letscareer.domain.blog.dto.request.UpdateBlogRequestDto;
import org.letscareer.letscareer.domain.blog.dto.response.blog.BlogsElementInfo;
import org.letscareer.letscareer.domain.blog.dto.response.blog.GetBlogResponseDto;
import org.letscareer.letscareer.domain.blog.dto.response.blog.GetBlogsResponseDto;
import org.letscareer.letscareer.domain.blog.entity.Blog;
import org.letscareer.letscareer.domain.blog.entity.HashTag;
import org.letscareer.letscareer.domain.blog.helper.BlogHashTagHelper;
import org.letscareer.letscareer.domain.blog.helper.BlogHelper;
import org.letscareer.letscareer.domain.blog.helper.HashTagHelper;
import org.letscareer.letscareer.domain.blog.mapper.BlogMapper;
import org.letscareer.letscareer.domain.blog.type.BlogType;
import org.letscareer.letscareer.domain.blog.vo.BlogDetailVo;
import org.letscareer.letscareer.domain.blog.vo.BlogThumbnailVo;
import org.letscareer.letscareer.domain.blog.vo.HashTagDetailInfo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BlogServiceImpl implements BlogService {
    private final BlogHelper blogHelper;
    private final BlogMapper blogMapper;
    private final HashTagHelper hashTagHelper;
    private final BlogHashTagHelper blogHashTagHelper;

    @Override
    public GetBlogsResponseDto getBlogs(BlogType type, Long tagId, Pageable pageable) {
        Page<BlogThumbnailVo> blogThumbnailVos = blogHelper.findBlogThumbnailVos(type, tagId, pageable);
        PageInfo pageInfo = PageInfo.of(blogThumbnailVos);
        List<BlogsElementInfo> blogsElementInfos = createBlogsElementInfos(blogThumbnailVos.getContent());
        return blogMapper.toGetBlogsResponseDto(blogsElementInfos, pageInfo);
    }

    @Override
    public GetBlogResponseDto getBlogDetail(Long blogId) {
        BlogDetailVo blogDetailInfo = blogHelper.findBlogDetailVoOrThrow(blogId);
        List<HashTagDetailInfo> tagDetailInfos = hashTagHelper.findTagDetailInfos(blogId);
        return blogMapper.toGetBlogResponseDto(blogDetailInfo, tagDetailInfos);
    }

    @Override
    public void createBlog(CreateBlogRequestDto requestDto) {
        Blog blog = blogHelper.createBlogAndSave(requestDto);
        List<HashTag> hashTags = hashTagHelper.findHashTags(requestDto.tagList());
        createBlogHashTags(blog, hashTags);
    }

    @Override
    public void updateBlog(Long blogId, UpdateBlogRequestDto requestDto) {
        Blog blog = updateBLog(blogId, requestDto);
        updateHashTags(blog, requestDto.tagList());
    }

    @Override
    public void deleteBlog(Long blogId) {
        blogHelper.deleteBlogById(blogId);
    }

    private List<BlogsElementInfo> createBlogsElementInfos(List<BlogThumbnailVo> blogThumbnailVos) {
        return blogThumbnailVos.stream()
                .map(blogThumbnailVo -> blogMapper.toBlogsElementInfo(
                        blogThumbnailVo,
                        hashTagHelper.findTagDetailInfos(blogThumbnailVo.id())
                ))
                .collect(Collectors.toList());
    }

    private Blog updateBLog(Long blogId, UpdateBlogRequestDto requestDto) {
        Blog blog = blogHelper.findBlogByIdByOrThrow(blogId);
        blog.updateBlog(requestDto);
        return blog;
    }

    private void updateHashTags(Blog blog, List<Long> tagList) {
        if (Objects.isNull(tagList)) return;
        List<HashTag> hashTags = hashTagHelper.findHashTags(tagList);
        blogHashTagHelper.deleteBlogHashTags(blog.getBlogHashTags());
        createBlogHashTags(blog, hashTags);
    }

    private void createBlogHashTags(Blog blog, List<HashTag> hashTags) {
        hashTags.forEach(hashTag -> {
            blogHashTagHelper.createBlogHashTagAndSave(blog, hashTag);
        });
    }
}
