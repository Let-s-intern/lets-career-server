package org.letscareer.letscareer.domain.report.type;

import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;

import java.util.Objects;

public enum ReportPaymentStatus {
    REPORT,
    ALL,
    REPORT_YET,
    ALL_YET;

    public static ReportPaymentStatus of(ReportApplication reportApplication, ReportFeedbackApplication reportFeedbackApplication) {
        boolean feedback = !Objects.isNull(reportFeedbackApplication);
        boolean yet = Objects.isNull(reportApplication.getApplyUrl());
        if(!feedback && !yet) return REPORT;
        else if(feedback && !yet) return ALL;
        else if(!feedback && yet) return REPORT_YET;
        return ALL_YET;
    }
}
