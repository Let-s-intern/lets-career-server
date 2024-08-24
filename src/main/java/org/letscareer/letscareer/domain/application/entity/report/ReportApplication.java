package org.letscareer.letscareer.domain.application.entity.report;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.application.type.ReportApplicationStatus;
import org.letscareer.letscareer.domain.application.type.converter.ReportApplicationStatusConverter;
import org.letscareer.letscareer.domain.file.entity.File;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.type.ReportPriceType;
import org.letscareer.letscareer.domain.report.type.ReportPriceTypeConverter;
import org.letscareer.letscareer.domain.user.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@DiscriminatorValue("report_application")
@Getter
@Entity
public class ReportApplication extends Application {
    private String wishJob;
    private String message;

    private LocalDateTime reportDate;

    private Integer price;
    private Integer discountPrice;

    @Convert(converter = ReportApplicationStatusConverter.class)
    private ReportApplicationStatus status;
    @Convert(converter = ReportPriceTypeConverter.class)
    private ReportPriceType reportPriceType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private Report report;
    @OneToMany(mappedBy = "reportApplication", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ReportApplicationOption> reportApplicationOptionList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "report_feedback_application_id")
    private ReportFeedbackApplication reportFeedbackApplication;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "report_file_id")
    private File reportFile;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "apply_file_id")
    private File applyFile;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "recruitment_file_id")
    private File recruitmentFile;

    @Builder(access = AccessLevel.PRIVATE)
    public ReportApplication(Report report, User user) {
        super(user);
        this.report = report;
    }
}
