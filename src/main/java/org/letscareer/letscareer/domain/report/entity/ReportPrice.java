package org.letscareer.letscareer.domain.report.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportPriceRequestDto;
import org.letscareer.letscareer.domain.report.type.ReportPriceType;
import org.letscareer.letscareer.domain.report.type.ReportPriceTypeConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "report_price")
@Entity
public class ReportPrice extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_price_id")
    private Long id;

    @Convert(converter = ReportPriceTypeConverter.class)
    private ReportPriceType reportPriceType;
    private Integer price;
    private Integer discountPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private Report report;

    public static ReportPrice createReportPrice(CreateReportPriceRequestDto requestDto, Report report) {
        return ReportPrice.builder()
                .reportPriceType(requestDto.reportPriceType())
                .price(requestDto.price())
                .discountPrice(requestDto.discountPrice())
                .report(report)
                .build();
    }
}
