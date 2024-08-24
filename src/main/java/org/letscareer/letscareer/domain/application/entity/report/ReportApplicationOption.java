package org.letscareer.letscareer.domain.application.entity.report;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("report_application_option")
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
}
