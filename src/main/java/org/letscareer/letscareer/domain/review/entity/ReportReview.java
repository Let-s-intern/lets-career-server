package org.letscareer.letscareer.domain.review.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.letscareer.letscareer.domain.report.type.ReportTypeConverter;

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
}
