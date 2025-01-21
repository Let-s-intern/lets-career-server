package org.letscareer.letscareer.domain.review.repository;

import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.review.vo.BlogReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.BlogReviewVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogReviewQueryRepository {
    List<BlogReviewAdminVo> findAllBlogReviewAdminVos();
    Page<BlogReviewVo> findAllBlogReviewVos(List<ProgramType> typeList, Pageable pageable);
}
