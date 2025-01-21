package org.letscareer.letscareer.domain.review.repository;

import org.letscareer.letscareer.domain.review.vo.BlogReviewAdminVo;

import java.util.List;

public interface BlogReviewQueryRepository {
    List<BlogReviewAdminVo> findAllBlogReviewAdminVos();
}
