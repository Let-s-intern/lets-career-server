package org.letscareer.letscareer.domain.review.repository;

import org.letscareer.letscareer.domain.review.vo.LiveReviewAdminVo;

import java.util.List;

public interface LiveReviewQueryRepository {
    List<LiveReviewAdminVo> findAllLiveReviewAdminVos();
}
