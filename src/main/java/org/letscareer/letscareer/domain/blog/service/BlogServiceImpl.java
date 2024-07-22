package org.letscareer.letscareer.domain.blog.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.dto.request.CreateBlogRequestDto;
import org.letscareer.letscareer.domain.blog.dto.request.UpdateBlogRequestDto;
import org.letscareer.letscareer.domain.blog.entity.Blog;
import org.letscareer.letscareer.domain.blog.entity.HashTag;
import org.letscareer.letscareer.domain.blog.helper.BlogHelper;
import org.letscareer.letscareer.domain.blog.helper.HashTagHelper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogServiceImpl implements BlogService {
    private final BlogHelper blogHelper;
    private final HashTagHelper hashTagHelper;

    @Override
    public void createBlog(CreateBlogRequestDto requestDto) {
        Blog blog = blogHelper.createBlogAndSave(requestDto);

    }

    @Override
    public void updateBlog(Long blogId, UpdateBlogRequestDto requestDto) {
        Blog blog = blogHelper.findBlogByIdByOrThrow(blogId);
    }
}
