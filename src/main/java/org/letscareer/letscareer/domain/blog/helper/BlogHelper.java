package org.letscareer.letscareer.domain.blog.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.dto.request.CreateBlogRequestDto;
import org.letscareer.letscareer.domain.blog.entity.Blog;
import org.letscareer.letscareer.domain.blog.repository.BlogRepository;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BlogHelper {
    private final BlogRepository blogRepository;

    public Blog createBlogAndSave(CreateBlogRequestDto requestDto) {
        Blog blog = Blog.createBlog(requestDto);
        return blogRepository.save(blog);
    }

    public Blog findBlogByIdByOrThrow(Long blogId) {
        return blogRepository.findById(blogId)
                .orElseThrow(() -> new EntityNotFoundException());
    }
}
