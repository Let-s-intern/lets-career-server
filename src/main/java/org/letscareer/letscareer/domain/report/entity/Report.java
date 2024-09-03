package org.letscareer.letscareer.domain.report.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportRequestDto;
import org.letscareer.letscareer.domain.report.dto.req.UpdateReportRequestDto;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.letscareer.letscareer.domain.report.type.ReportTypeConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils.updateValue;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "report")
@Entity
public class Report extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @Convert(converter = ReportTypeConverter.class)
    private ReportType type;

    private String title;

    private String contents;

    private String notice;

    private LocalDateTime visibleDate;

    @OneToMany(mappedBy = "report", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<ReportOption> optionList = new ArrayList<>();

    @OneToMany(mappedBy = "report", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<ReportPrice> priceList = new ArrayList<>();

    @OneToMany(mappedBy = "report", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<ReportApplication> applicationList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "report_feedback_id")
    private ReportFeedback reportFeedback;

    public static Report createReport(CreateReportRequestDto requestDto) {
        return Report.builder()
                .type(requestDto.reportType())
                .title(requestDto.title())
                .contents(requestDto.contents())
                .notice(requestDto.notice())
                .visibleDate(requestDto.visibleDate())
                .build();
    }

    public void setReportFeedback(ReportFeedback reportFeedback) {
        this.reportFeedback = reportFeedback;
    }

    public void updateReport(UpdateReportRequestDto requestDto) {
        this.type = updateValue(this.type, requestDto.reportType());
        this.visibleDate = requestDto.visibleDate();
        this.title = updateValue(this.title, requestDto.title());
        this.contents = updateValue(this.contents, requestDto.contents());
        this.notice = updateValue(this.notice, requestDto.notice());
    }

    public void setInitReportPrices() {
        this.priceList = new ArrayList<>();
    }

    public void setInitReportOptions() {
        this.optionList = new ArrayList<>();
    }

    public void setInitReportFeedback() {
        this.reportFeedback = null;
    }


    public void addApplication(ReportApplication reportApplication) {
        this.applicationList.add(reportApplication);
    }
}
