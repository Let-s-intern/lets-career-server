package org.letscareer.letscareer.domain.blog.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.entity.Blog;
import org.letscareer.letscareer.domain.blog.entity.BlogLike;
import org.letscareer.letscareer.domain.blog.repository.LikeQueryRepository;
import org.letscareer.letscareer.domain.blog.repository.BlogLikeRepository;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.ConflictException;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.letscareer.letscareer.domain.blog.error.BlogErrorCode.BLOG_LIKE_ALREADY_EXISTS;

@RequiredArgsConstructor
@Component
public class LikeHelper {
    private final BlogLikeRepository blogLikeRepository;

    public void addBlogLike(Blog blog, User user) {
        if (blogLikeRepository.existsByBlogAndUser(blog.getId(), user.getId()))
            throw new ConflictException(BLOG_LIKE_ALREADY_EXISTS);

        BlogLike blogLike = BlogLike.createBlogLike(blog, user);
        blogLikeRepository.save(blogLike);
    }

    public List<Long> getUserLikedBlogIds(Long userId){
        return blogLikeRepository.findUserLikedBlogIds(userId);
    }
}
