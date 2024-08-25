package org.letscareer.letscareer.domain.report.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportFeedbackRequestDto;
import org.letscareer.letscareer.domain.report.type.ReportPriceType;
import org.letscareer.letscareer.domain.report.type.ReportPriceTypeConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import static org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils.updateValue;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "report_feedback")
@Entity
public class ReportFeedback extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_feedback_id")
    private Long id;

    @Convert(converter = ReportPriceTypeConverter.class)
    @Builder.Default
    private ReportPriceType reportPriceType = ReportPriceType.BASIC;
    private Integer feedbackPrice;
    private Integer feedbackDiscountPrice;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "report_id")
    private Report report;

    public static ReportFeedback createReportFeedback(CreateReportFeedbackRequestDto requestDto, Report report) {
        return ReportFeedback.builder()
                .feedbackPrice(requestDto.price())
                .feedbackDiscountPrice(requestDto.discountPrice())
                .report(report)
                .build();
    }

    public void updateReportFeedback(CreateReportFeedbackRequestDto requestDto) {
        this.feedbackPrice = updateValue(this.feedbackPrice, requestDto.price());
        this.feedbackDiscountPrice = updateValue(this.feedbackDiscountPrice, requestDto.discountPrice());
    }
}
