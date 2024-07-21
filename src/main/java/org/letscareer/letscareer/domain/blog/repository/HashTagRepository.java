package org.letscareer.letscareer.domain.blog.repository;

import org.letscareer.letscareer.domain.blog.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<HashTag, Long> {
}
