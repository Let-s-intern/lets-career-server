package org.letscareer.letscareer.domain.review.repository;

import org.letscareer.letscareer.domain.review.vo.ReviewInfoVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryRepository {
    Page<ReviewInfoVo> findAllReviewInfoVos(Pageable pageable);
}
