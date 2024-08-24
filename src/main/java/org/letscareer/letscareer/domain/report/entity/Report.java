package org.letscareer.letscareer.domain.report.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.letscareer.letscareer.domain.report.type.ReportTypeConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ReportOption> optionList = new ArrayList<>();

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ReportPrice> priceList = new ArrayList<>();

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ReportApplication> applicationList = new ArrayList<>();

}
