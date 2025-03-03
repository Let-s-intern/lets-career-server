package org.letscareer.letscareer.domain.blog.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.dto.request.CreateBlogRequestDto;
import org.letscareer.letscareer.domain.blog.entity.Blog;
import org.letscareer.letscareer.domain.blog.repository.BlogRepository;
import org.letscareer.letscareer.domain.blog.type.BlogType;
import org.letscareer.letscareer.domain.blog.vo.BlogDetailVo;
import org.letscareer.letscareer.domain.blog.vo.BlogThumbnailVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.letscareer.letscareer.domain.blog.error.BlogErrorCode.BLOG_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class BlogHelper {
    private final BlogRepository blogRepository;

    public Page<BlogThumbnailVo> findBlogThumbnailVos(User user, List<BlogType> types, Long tagId, Pageable pageable) {
        return blogRepository.findBlogThumbnailVos(user, types, tagId, pageable);
    }

    public BlogDetailVo findBlogDetailVoOrThrow(Long blogId) {
        return blogRepository.findBlogDetailVo(blogId)
                .orElseThrow(() -> new EntityNotFoundException(BLOG_NOT_FOUND));
    }

    public Blog createBlogAndSave(CreateBlogRequestDto requestDto) {
        Blog blog = Blog.createBlog(requestDto);
        return blogRepository.save(blog);
    }

    public Blog findBlogByIdByOrThrow(Long blogId) {
        return blogRepository.findById(blogId)
                .orElseThrow(() -> new EntityNotFoundException(BLOG_NOT_FOUND));
    }

    public void deleteBlogById(Long blogId) {
        blogRepository.deleteById(blogId);
    }
}
