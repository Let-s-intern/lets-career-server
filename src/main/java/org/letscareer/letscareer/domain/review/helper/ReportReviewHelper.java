package org.letscareer.letscareer.domain.review.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.entity.ReportReview;
import org.letscareer.letscareer.domain.review.repository.ReportReviewRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReportReviewHelper {
    private final ReportReviewRepository reportReviewRepository;

    public ReportReview createReportReviewAndSave(Report report, ReportApplication reportApplication, CreateReviewRequestDto requestDto) {
        ReportReview reportReview = ReportReview.createReportReview(report, reportApplication, requestDto);
        return reportReviewRepository.save(reportReview);
    }
}
