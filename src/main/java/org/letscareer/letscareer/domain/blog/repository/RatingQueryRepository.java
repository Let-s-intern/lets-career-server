package org.letscareer.letscareer.domain.blog.repository;

import org.letscareer.letscareer.domain.blog.vo.RatingDetailVo;
import org.letscareer.letscareer.domain.blog.vo.RatingVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RatingQueryRepository {
    List<RatingDetailVo> findAllRatingDetailVosByBlogId(Long blogId);
    Page<RatingVo> findRatingVos(Pageable pageable);
}
