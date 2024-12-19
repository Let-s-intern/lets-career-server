package org.letscareer.letscareer.domain.application.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;
import org.letscareer.letscareer.domain.application.repository.report.ReportFeedbackApplicationRepository;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.type.UserRole;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.letscareer.letscareer.global.error.exception.UnauthorizedException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static org.letscareer.letscareer.domain.report.error.ReportErrorCode.REPORT_FEEDBACK_APPLICATION_NOT_FOUND;
import static org.letscareer.letscareer.global.error.GlobalErrorCode.UNAUTHORIZED;

@RequiredArgsConstructor
@Component
public class ReportFeedbackApplicationHelper {
    private final ReportFeedbackApplicationRepository reportFeedbackApplicationRepository;

    public ReportFeedbackApplication findReportFeedbackApplicationByReportApplicationIdOrElseNull(Long reportApplicationId) {
        return reportFeedbackApplicationRepository.findByReportApplicationId(reportApplicationId).orElse(null);
    }

    public ReportFeedbackApplication findReportFeedbackApplicationByReportFeedbackApplicationIdOrThrow(Long reportFeedbackApplicationId) {
        return reportFeedbackApplicationRepository.findById(reportFeedbackApplicationId)
                .orElseThrow(() -> new EntityNotFoundException(REPORT_FEEDBACK_APPLICATION_NOT_FOUND));
    }

    public void validateAuthorizedUser(User feedbackApplicationUser, User currentUser) {
        if (!currentUser.getRole().equals(UserRole.ADMIN) && (!Objects.equals(feedbackApplicationUser.getId(), currentUser.getId()))) {
            throw new UnauthorizedException(UNAUTHORIZED);
        }
    }

    public List<Long> findDdayNotificationReportFeedbackApplicationIds() {
        return reportFeedbackApplicationRepository.findDdayNotificationReportFeedbackApplicationIds();
    }

    public List<Long> findReviewNotificationReportFeedbackApplicationIds() {
        return reportFeedbackApplicationRepository.findAllReviewNotificationReportFeedbackApplicationId();
    }
}
