package org.letscareer.letscareer.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.letscareer.letscareer.domain.report.type.ReportTypeConverter;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("report_review")
@Getter
@Entity
public class ReportReview extends Review {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private Report report;

    @Convert(converter = ReportTypeConverter.class)
    private ReportType reportType;

    @Builder(access = AccessLevel.PRIVATE)
    public ReportReview(Report report, ReportApplication reportApplication, CreateReviewRequestDto requestDto) {
        super(reportApplication, requestDto);
        this.report = report;
        this.reportType = report.getType();
    }

    public static ReportReview createReportReview(Report report, ReportApplication reportApplication, CreateReviewRequestDto requestDto) {
        ReportReview reportReview = ReportReview.builder()
                .report(report)
                .reportApplication(reportApplication)
                .requestDto(requestDto)
                .build();
        reportApplication.setReview(reportReview);
        return reportReview;
    }
}
