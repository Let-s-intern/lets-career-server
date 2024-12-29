package org.letscareer.letscareer.domain.faq.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.report.entity.Report;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "faq_report")
@Entity
public class FaqReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faq_report_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private Report report;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faq_id")
    private Faq faq;

    public static FaqReport createFaqReport(Faq faq,
                                            Report report) {
        FaqReport faqReport = FaqReport.builder()
                .report(report)
                .faq(faq)
                .build();
        faq.addFaqReportList(faqReport);
        report.addFaqReportList(faqReport);
        return faqReport;
    }
}
