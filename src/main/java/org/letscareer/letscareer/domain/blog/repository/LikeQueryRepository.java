package org.letscareer.letscareer.domain.blog.repository;

import java.util.List;

public interface LikeQueryRepository {
    Boolean existsByBlogAndUser(Long blogId, Long userId);
    List<Long> findUserLikedBlogIds(Long userId);
}
