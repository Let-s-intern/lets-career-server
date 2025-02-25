package org.letscareer.letscareer.domain.blog.repository;

import org.letscareer.letscareer.domain.blog.entity.Blog;
import org.letscareer.letscareer.domain.blog.entity.BlogLike;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogLikeRepository extends JpaRepository<BlogLike, Long> {
    Boolean existsByBlogAndUser(Blog blog, User user);
}
