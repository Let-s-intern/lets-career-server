package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.res.GetReportsForAdminResponseDto;
import org.springframework.data.domain.Pageable;

public interface GetReportsForAdminService {
    GetReportsForAdminResponseDto execute(Pageable pageable);
}
