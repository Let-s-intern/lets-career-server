package org.letscareer.letscareer.domain.application.entity.report;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.letscareer.letscareer.domain.application.type.ReportDesiredDateType;
import org.letscareer.letscareer.domain.application.type.ReportFeedbackStatus;
import org.letscareer.letscareer.domain.application.type.converter.ReportDesiredDateTypeConverter;
import org.letscareer.letscareer.domain.application.type.converter.ReportFeedbackStatusConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("report_feedback_application")
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
    private ReportFeedbackStatus reportFeedbackStatus;
    private LocalDateTime checkedDate;

    private String zoomLink;
    private String zoomPassword;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "report_application_id")
    private ReportApplication reportApplication;
}
