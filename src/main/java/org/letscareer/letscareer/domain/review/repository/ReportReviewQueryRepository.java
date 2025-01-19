package org.letscareer.letscareer.domain.review.repository;

import org.letscareer.letscareer.domain.review.vo.ReportReviewAdminVo;

import java.util.List;

public interface ReportReviewQueryRepository {
    List<ReportReviewAdminVo> findAllReportReviewAdminVos();
}
