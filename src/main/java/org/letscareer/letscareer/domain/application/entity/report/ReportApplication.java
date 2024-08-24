package org.letscareer.letscareer.domain.application.entity.report;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.application.type.ReportApplicationStatus;
import org.letscareer.letscareer.domain.application.type.converter.ReportApplicationStatusConverter;
import org.letscareer.letscareer.domain.report.entity.Report;
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
    private Integer price;
    private Integer discountPrice;
    private String wishJob;
    private String message;
    private String applyUrl;
    private String recruitmentUrl;
    private String reportUrl;
    private LocalDateTime reportDate;

    @Convert(converter = ReportApplicationStatusConverter.class)
    private ReportApplicationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private Report report;

    @OneToMany(mappedBy = "reportApplication", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ReportApplicationOption> reportApplicationOptionList = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    public ReportApplication(Report report, User user) {
        super(user);
        this.report = report;
    }
}
