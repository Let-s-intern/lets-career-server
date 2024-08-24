package org.letscareer.letscareer.domain.report.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.letscareer.letscareer.domain.report.type.ReportTypeConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;

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

}
