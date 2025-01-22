package org.letscareer.letscareer.domain.review.repository;

import org.letscareer.letscareer.domain.review.vo.ReportReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.ReportReviewVo;

import java.util.List;
import java.util.Optional;

public interface ReportReviewQueryRepository {
    List<ReportReviewAdminVo> findAllReportReviewAdminVos();
    Optional<ReportReviewVo> findReportReviewByReviewId(Long reviewId);
}
