package org.letscareer.letscareer.domain.application.entity.report;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.application.listener.ReportApplicationEntityListener;
import org.letscareer.letscareer.domain.application.type.ReportApplicationStatus;
import org.letscareer.letscareer.domain.application.type.converter.ReportApplicationStatusConverter;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportApplicationRequestDto;
import org.letscareer.letscareer.domain.report.dto.req.UpdateReportDocumentRequestDto;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.entity.ReportPrice;
import org.letscareer.letscareer.domain.report.type.ReportPriceType;
import org.letscareer.letscareer.domain.report.type.ReportPriceTypeConverter;
import org.letscareer.letscareer.domain.user.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils.updateValue;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("report_application")
@Getter
@EntityListeners(ReportApplicationEntityListener.class)
@Entity
public class ReportApplication extends Application {
    private String wishJob;
    private String message;

    private LocalDateTime reportDate;

    private Integer price;
    private Integer discountPrice;

    private String reportUrl;
    private String applyUrl;
    private String recruitmentUrl;

    private Integer refundPrice;

    @Convert(converter = ReportApplicationStatusConverter.class)
    private ReportApplicationStatus status = ReportApplicationStatus.APPLIED;
    @Convert(converter = ReportPriceTypeConverter.class)
    private ReportPriceType reportPriceType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private Report report;
    @OneToMany(mappedBy = "reportApplication", cascade = CascadeType.ALL)
    private List<ReportApplicationOption> reportApplicationOptionList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "report_feedback_application_id")
    private ReportFeedbackApplication reportFeedbackApplication;

    @Builder(access = AccessLevel.PRIVATE)
    public ReportApplication(CreateReportApplicationRequestDto requestDto,
                             Report report,
                             ReportPrice reportPrice,
                             User user) {
        super(user);
        this.wishJob = requestDto.wishJob();
        this.message = requestDto.message();
        this.price = reportPrice.getPrice();
        this.discountPrice = reportPrice.getDiscountPrice();
        this.reportPriceType = requestDto.reportPriceType();
        this.applyUrl = requestDto.applyUrl();
        this.recruitmentUrl = requestDto.recruitmentUrl();
        this.report = report;
        this.status = ReportApplicationStatus.APPLIED;
        this.reportApplicationOptionList = new ArrayList<>();
    }

    public static ReportApplication createReportApplication(CreateReportApplicationRequestDto requestDto,
                                                            Report report,
                                                            ReportPrice reportPrice,
                                                            User user) {
        ReportApplication reportApplication = ReportApplication.builder()
                .requestDto(requestDto)
                .report(report)
                .reportPrice(reportPrice)
                .user(user)
                .build();
        report.addApplication(reportApplication);
        return reportApplication;
    }

    public void setReportFeedbackApplication(ReportFeedbackApplication reportFeedbackApplication) {
        this.reportFeedbackApplication = reportFeedbackApplication;
    }

    public void addReportApplicationOption(ReportApplicationOption reportApplicationOption) {
        this.reportApplicationOptionList.add(reportApplicationOption);
    }

    public void updateReportUrl(UpdateReportDocumentRequestDto requestDto) {
        this.reportUrl = requestDto.reportUrl();
        this.status = ReportApplicationStatus.REPORTED;
    }

    public void updateReportApplicationStatus(ReportApplicationStatus status) {
        this.status = updateValue(this.status, status);
    }
}
