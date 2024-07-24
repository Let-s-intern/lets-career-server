package org.letscareer.letscareer.domain.blog.service;

import org.letscareer.letscareer.domain.blog.dto.request.CreateBlogRequestDto;
import org.letscareer.letscareer.domain.blog.dto.request.UpdateBlogRequestDto;
import org.letscareer.letscareer.domain.blog.dto.response.blog.GetBlogResponseDto;
import org.letscareer.letscareer.domain.blog.dto.response.blog.GetBlogsResponseDto;
import org.letscareer.letscareer.domain.blog.type.BlogType;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface BlogService {
    GetBlogsResponseDto getBlogs(BlogType type, Long tagId, Pageable pageable);

    GetBlogResponseDto getBlogDetail(Long blogId);

    void createBlog(CreateBlogRequestDto requestDto);

    void updateBlog(Long blogId, UpdateBlogRequestDto requestDto);

    void deleteBlog(Long blogId);
}
