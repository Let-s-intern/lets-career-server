package org.letscareer.letscareer.domain.application.entity.report;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.report.entity.ReportOption;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class ReportApplicationOption extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_application_option_id")
    private Long id;

    private Integer price;
    private Integer discountPrice;

    private String title;
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_application_id")
    private ReportApplication reportApplication;

    public static ReportApplicationOption createReportApplicationOption(ReportApplication reportApplication,
                                                                        ReportOption reportOption) {
        ReportApplicationOption reportApplicationOption = ReportApplicationOption.builder()
                .price(reportOption.getPrice())
                .discountPrice(reportOption.getDiscountPrice())
                .title(reportOption.getTitle())
                .code(reportOption.getCode())
                .reportApplication(reportApplication)
                .build();
        reportApplication.addReportApplicationOption(reportApplicationOption);
        return reportApplicationOption;
    }
}
