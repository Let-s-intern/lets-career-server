package org.letscareer.letscareer.domain.application.entity.report;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.application.type.ReportDesiredDateType;
import org.letscareer.letscareer.domain.application.type.ReportFeedbackStatus;
import org.letscareer.letscareer.domain.application.type.converter.ReportDesiredDateTypeConverter;
import org.letscareer.letscareer.domain.application.type.converter.ReportFeedbackStatusConverter;
import org.letscareer.letscareer.domain.program.dto.response.ZoomMeetingResponseDto;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportApplicationRequestDto;
import org.letscareer.letscareer.domain.report.entity.ReportFeedback;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class ReportFeedbackApplication extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_feedback_application_id")
    private Long id;

    private Integer price;
    private Integer discountPrice;

    private LocalDateTime desiredDate1;
    private LocalDateTime desiredDate2;
    private LocalDateTime desiredDate3;
    private LocalDateTime desiredDateAdmin;

    @Convert(converter = ReportDesiredDateTypeConverter.class)
    private ReportDesiredDateType desiredDateType;
    @Convert(converter = ReportFeedbackStatusConverter.class)
    @Builder.Default
    private ReportFeedbackStatus reportFeedbackStatus = ReportFeedbackStatus.APPLIED;
    private LocalDateTime checkedDate;

    private String zoomLink;
    private String zoomPassword;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "report_application_id")
    private ReportApplication reportApplication;

    public static ReportFeedbackApplication createReportFeedbackApplication(CreateReportApplicationRequestDto requestDto,
                                                                            ReportFeedback reportFeedback,
                                                                            ReportApplication reportApplication) {
        ReportFeedbackApplication reportFeedbackApplication = ReportFeedbackApplication.builder()
                .price(reportFeedback.getFeedbackPrice())
                .discountPrice(reportFeedback.getFeedbackDiscountPrice())
                .desiredDate1(requestDto.desiredDate1())
                .desiredDate2(requestDto.desiredDate2())
                .desiredDate3(requestDto.desiredDate3())
                .reportApplication(reportApplication)
                .build();
        reportApplication.setReportFeedbackApplication(reportFeedbackApplication);
        return reportFeedbackApplication;
    }
}
