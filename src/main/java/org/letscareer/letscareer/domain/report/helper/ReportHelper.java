package org.letscareer.letscareer.domain.report.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.report.repository.ReportRepository;
import org.letscareer.letscareer.domain.report.vo.ReportForAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReportHelper {
    private final ReportRepository reportRepository;

    public Page<ReportForAdminVo> findReportForAdminInfos(Pageable pageable) {
        return reportRepository.findReportForAdminInfos(pageable);
    }
}
