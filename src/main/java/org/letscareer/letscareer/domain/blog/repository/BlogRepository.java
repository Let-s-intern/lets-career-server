package org.letscareer.letscareer.domain.blog.repository;

import org.letscareer.letscareer.domain.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
