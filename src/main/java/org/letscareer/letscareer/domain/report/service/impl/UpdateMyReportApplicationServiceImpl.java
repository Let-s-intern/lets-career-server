package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.nhn.dto.request.RequestMessageInfo;
import org.letscareer.letscareer.domain.nhn.dto.request.report.FeedbackNotiParameter;
import org.letscareer.letscareer.domain.nhn.dto.request.report.ReportNotificationParameter;
import org.letscareer.letscareer.domain.nhn.provider.NhnProvider;
import org.letscareer.letscareer.domain.report.dto.req.UpdateMyReportApplicationRequestDto;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.helper.ReportOptionHelper;
import org.letscareer.letscareer.domain.report.service.UpdateMyReportApplicationService;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.type.UserRole;
import org.letscareer.letscareer.global.common.utils.redis.utils.RedisUtils;
import org.letscareer.letscareer.global.error.exception.UnauthorizedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.letscareer.letscareer.domain.report.error.ReportErrorCode.REPORT_CANNOT_UPDATED;
import static org.letscareer.letscareer.domain.report.service.impl.CreateReportApplicationServiceImpl.REPORT_APPLICATION_CACHE_KEY;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateMyReportApplicationServiceImpl implements UpdateMyReportApplicationService {
    private final ReportApplicationHelper reportApplicationHelper;
    private final ReportOptionHelper reportOptionHelper;
    private final NhnProvider nhnProvider;
    private final RedisUtils redisUtils;

    @Override
    public void execute(User user, Long applicationId, UpdateMyReportApplicationRequestDto requestDto) {
        ReportApplication reportApplication = reportApplicationHelper.findReportApplicationByReportApplicationIdOrThrow(applicationId);
        validateUpdatableUser(reportApplication.getUser(), user);
        reportApplication.updateMyReportApplication(requestDto);
        ReportFeedbackApplication reportFeedbackApplication = reportApplication.getReportFeedbackApplication();
        if(!Objects.isNull(reportFeedbackApplication)) reportFeedbackApplication.updateDesiredDates(requestDto);

        sendKakaoMessages(user, reportApplication, reportFeedbackApplication);
        redisUtils.delete(REPORT_APPLICATION_CACHE_KEY + applicationId);
    }

    private void validateUpdatableUser(User user, User currentUser) {
        if(currentUser.getRole().equals(UserRole.USER) && !currentUser.getId().equals(user.getId())) {
            throw new UnauthorizedException(REPORT_CANNOT_UPDATED);
        }
    }

    private void sendKakaoMessages(User user, ReportApplication reportApplication, ReportFeedbackApplication reportFeedbackApplication) {
        List<RequestMessageInfo<?>> messageList = new ArrayList<>();
        Report report = reportApplication.getReport();
        String reportOptionListStr = reportOptionHelper.createReportOptionListStr(reportApplication.getReportApplicationOptionList());
        ReportNotificationParameter reportNotificationParameter = ReportNotificationParameter.of(user.getName(), report.getTitle(), report.getType().getDesc(), reportOptionListStr);
        messageList.add(RequestMessageInfo.of(reportNotificationParameter, "report_notification"));

        if (!Objects.isNull(reportFeedbackApplication)) {
            FeedbackNotiParameter feedbackNotiParameter = FeedbackNotiParameter.of(user.getName(), report, reportOptionListStr, reportFeedbackApplication);
            messageList.add(RequestMessageInfo.of(feedbackNotiParameter, "feedback_noti"));
        }
        nhnProvider.sendPaymentKakaoMessages(user, messageList);
    }
}
