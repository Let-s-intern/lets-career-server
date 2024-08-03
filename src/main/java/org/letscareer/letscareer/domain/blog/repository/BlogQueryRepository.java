package org.letscareer.letscareer.domain.blog.repository;

import org.letscareer.letscareer.domain.blog.type.BlogType;
import org.letscareer.letscareer.domain.blog.vo.BlogDetailVo;
import org.letscareer.letscareer.domain.blog.vo.BlogThumbnailVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BlogQueryRepository {
    Optional<BlogDetailVo> findBlogDetailVo(Long blogId);

    Page<BlogThumbnailVo> findBlogThumbnailVos(User user, BlogType type, Long tagId, Pageable pageable);
}
