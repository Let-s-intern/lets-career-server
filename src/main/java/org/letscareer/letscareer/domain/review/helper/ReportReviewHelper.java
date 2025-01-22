package org.letscareer.letscareer.domain.review.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.entity.ReportReview;
import org.letscareer.letscareer.domain.review.repository.ReportReviewRepository;
import org.letscareer.letscareer.domain.review.vo.ReportReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.ReportReviewVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.letscareer.letscareer.domain.review.error.ReviewErrorCode.REVIEW_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class ReportReviewHelper {
    private final ReportReviewRepository reportReviewRepository;

    public ReportReview createReportReviewAndSave(Report report, ReportApplication reportApplication, CreateReviewRequestDto requestDto) {
        ReportReview reportReview = ReportReview.createReportReview(report, reportApplication, requestDto);
        return reportReviewRepository.save(reportReview);
    }

    public ReportReview findReportReviewByReviewIdOrThrow(Long reviewId) {
        return reportReviewRepository.findById(reviewId).orElseThrow(() -> new EntityNotFoundException(REVIEW_NOT_FOUND));
    }

    public List<ReportReviewAdminVo> findAllReportReviewAdminVos() {
        return reportReviewRepository.findAllReportReviewAdminVos();
    }

    public ReportReviewVo findReportReviewVoOrThrow(Long reviewId) {
        return reportReviewRepository.findReportReviewByReviewId(reviewId).orElseThrow(() -> new EntityNotFoundException(REVIEW_NOT_FOUND));
    }
}
