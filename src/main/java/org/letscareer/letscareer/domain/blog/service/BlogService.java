package org.letscareer.letscareer.domain.blog.service;

import org.letscareer.letscareer.domain.blog.dto.request.CreateBlogRequestDto;
import org.letscareer.letscareer.domain.blog.dto.request.UpdateBlogRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface BlogService {
    void createBlog(CreateBlogRequestDto requestDto);
    void updateBlog(Long blogId, UpdateBlogRequestDto requestDto);
}
