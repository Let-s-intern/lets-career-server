package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.faq.dto.response.GetFaqResponseDto;

public interface GetReportFaqsService {
    GetFaqResponseDto execute(Long reportId);
}
