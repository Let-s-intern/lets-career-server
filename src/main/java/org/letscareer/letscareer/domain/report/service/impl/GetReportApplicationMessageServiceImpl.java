package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.report.dto.res.GetReportApplicationMessageResponseDto;
import org.letscareer.letscareer.domain.report.mapper.ReportMapper;
import org.letscareer.letscareer.domain.report.service.GetReportApplicationMessageService;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.UnauthorizedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static org.letscareer.letscareer.global.error.GlobalErrorCode.UNAUTHORIZED;

@RequiredArgsConstructor
@Transactional
@Service
public class GetReportApplicationMessageServiceImpl implements GetReportApplicationMessageService {
    private final ReportApplicationHelper reportApplicationHelper;
    private final ReportMapper reportMapper;

    @Override
    public GetReportApplicationMessageResponseDto execute(User user, Long applicationId) {
        ReportApplication reportApplication = reportApplicationHelper.findReportApplicationByReportApplicationIdOrThrow(applicationId);
        validateAuthorizedUser(user, reportApplication.getUser());
        return reportMapper.toGetReportApplicationMessageResponseDto(reportApplication.getMessage());
    }

    private void validateAuthorizedUser(User currentUser, User applicationUser) {
        if (!Objects.equals(currentUser.getId(), applicationUser.getId())) {
            throw new UnauthorizedException(UNAUTHORIZED);
        }
    }
}
