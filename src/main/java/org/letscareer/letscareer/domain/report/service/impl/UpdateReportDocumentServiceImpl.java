package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.report.dto.req.UpdateReportDocumentRequestDto;
import org.letscareer.letscareer.domain.report.service.UpdateReportDocumentService;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.type.UserRole;
import org.letscareer.letscareer.global.error.exception.UnauthorizedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateReportDocumentServiceImpl implements UpdateReportDocumentService {
    private final ReportApplicationHelper reportApplicationHelper;

    @Override
    public void execute(User user, Long applicationId, UpdateReportDocumentRequestDto requestDto) {
        validateAdminRole(user);
        ReportApplication reportApplication = reportApplicationHelper.findReportApplicationByReportApplicationIdOrThrow(applicationId);
        reportApplication.updateReportUrl(requestDto);
    }

    private void validateAdminRole(User currentUser) {
        if(!currentUser.getRole().equals(UserRole.ADMIN)) {
            throw new UnauthorizedException();
        }
    }
}
