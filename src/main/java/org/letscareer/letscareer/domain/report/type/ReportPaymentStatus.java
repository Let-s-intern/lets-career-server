package org.letscareer.letscareer.domain.report.type;

public enum ReportPaymentStatus {
    REPORT,
    ALL,
    REPORT_YET,
    ALL_YET;

    public ReportPaymentStatus of(Boolean feedback, Boolean yet) {
        if(!feedback && !yet) return REPORT;
        else if(feedback && !yet) return ALL;
        else if(!feedback && yet) return REPORT_YET;
        return ALL_YET;
    }
}
