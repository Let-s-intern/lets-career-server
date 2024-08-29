package org.letscareer.letscareer.domain.report.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplicationOption;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportOptionRequestDto;
import org.letscareer.letscareer.domain.report.type.ReportPriceType;
import org.letscareer.letscareer.domain.report.type.ReportPriceTypeConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "report_option")
@Entity
public class ReportOption extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_option_id")
    private Long id;

    @Convert(converter = ReportPriceTypeConverter.class)
    @Builder.Default
    private ReportPriceType reportPriceType = ReportPriceType.BASIC;
    private Integer price;
    private Integer discountPrice;

    private String title;

    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private Report report;

    public static ReportOption createReportOption(CreateReportOptionRequestDto requestDto, Report report) {
        return ReportOption.builder()
                .price(requestDto.price())
                .discountPrice(requestDto.discountPrice())
                .title(requestDto.title())
                .code(requestDto.code())
                .report(report)
                .build();
    }
}
