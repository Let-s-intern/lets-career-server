package org.letscareer.letscareer.domain.blog.repository;

import org.letscareer.letscareer.domain.blog.vo.RatingDetailVo;

import java.util.List;

public interface RatingQueryRepository {
    List<RatingDetailVo> findAllRatingDetailVosByBlogId(Long blogId);
}
