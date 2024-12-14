package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.report.dto.req.UpdateMyReportApplicationRequestDto;
import org.letscareer.letscareer.domain.report.service.UpdateMyReportApplicationService;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.type.UserRole;
import org.letscareer.letscareer.global.error.exception.UnauthorizedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static org.letscareer.letscareer.domain.report.error.ReportErrorCode.REPORT_CANNOT_UPDATED;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateMyReportApplicationServiceImpl implements UpdateMyReportApplicationService {
    private final ReportApplicationHelper reportApplicationHelper;

    @Override
    public void execute(User user, Long applicationId, UpdateMyReportApplicationRequestDto requestDto) {
        ReportApplication reportApplication = reportApplicationHelper.findReportApplicationByReportApplicationIdOrThrow(applicationId);
        validateUpdatableUser(reportApplication.getUser(), user);
        reportApplication.updateMyReportApplication(requestDto);
        ReportFeedbackApplication reportFeedbackApplication = reportApplication.getReportFeedbackApplication();
        if(!Objects.isNull(reportFeedbackApplication)) reportFeedbackApplication.updateDesiredDates(requestDto);
    }

    private void validateUpdatableUser(User user, User currentUser) {
        if(currentUser.getRole().equals(UserRole.USER) && !currentUser.getId().equals(user.getId())) {
            throw new UnauthorizedException(REPORT_CANNOT_UPDATED);
        }
    }
}
