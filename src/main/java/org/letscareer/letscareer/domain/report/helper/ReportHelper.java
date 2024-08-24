package org.letscareer.letscareer.domain.report.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.vo.ReportApplicationForAdminVo;
import org.letscareer.letscareer.domain.report.error.ReportErrorCode;
import org.letscareer.letscareer.domain.report.repository.ReportRepository;
import org.letscareer.letscareer.domain.report.vo.ReportDetailForAdminVo;
import org.letscareer.letscareer.domain.report.vo.ReportForAdminVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import static org.letscareer.letscareer.domain.report.error.ReportErrorCode.REPORT_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class ReportHelper {
    private final ReportRepository reportRepository;

    public Page<ReportForAdminVo> findReportForAdminInfos(Pageable pageable) {
        return reportRepository.findReportForAdminInfos(pageable);
    }

    public ReportDetailForAdminVo findReportDetailForAdminVoOrThrow(Long reportId) {
        return reportRepository.findReportDetailForAdminVo(reportId)
                .orElseThrow(() -> new EntityNotFoundException(REPORT_NOT_FOUND));
    }

    public Page<ReportApplicationForAdminVo> findReportApplicationForAdminVos(Long reportId, Pageable pageable) {
        return reportRepository.findReportApplicationForAdminVos(reportId, pageable);
    }
}
