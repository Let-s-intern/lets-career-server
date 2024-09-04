package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.application.type.ReportFeedbackStatus;
import org.letscareer.letscareer.domain.program.dto.response.ZoomMeetingResponseDto;
import org.letscareer.letscareer.domain.report.dto.req.UpdateFeedbackScheduleRequestDto;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.helper.ReportHelper;
import org.letscareer.letscareer.domain.report.service.UpdateReportFeedbackSchedule;
import org.letscareer.letscareer.global.common.utils.zoom.ZoomUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateReportFeedbackScheduleImpl implements UpdateReportFeedbackSchedule {
    private final ReportHelper reportHelper;
    private final ReportApplicationHelper reportApplicationHelper;
    private final ZoomUtils zoomUtils;

    @Override
    public void execute(Long reportId, Long applicationId, UpdateFeedbackScheduleRequestDto requestDto) {
        Report report = reportHelper.findReportByReportIdOrThrow(reportId);
        ReportFeedbackApplication reportFeedbackApplication = reportApplicationHelper.findReportFeedbackApplicationByApplicationId(applicationId);

        updateFeedbackStatusToConfirmed(reportFeedbackApplication, requestDto);
        updateFeedbackSchedule(report, reportFeedbackApplication, requestDto);
    }

    private void updateFeedbackStatusToConfirmed(ReportFeedbackApplication reportFeedbackApplication, UpdateFeedbackScheduleRequestDto requestDto) {
        if (Objects.isNull(requestDto.reportFeedbackStatus())) return;
        if (!ReportFeedbackStatus.CONFIRMED.equals(requestDto.reportFeedbackStatus())) return;
        reportFeedbackApplication.updateFeedbackStatus(requestDto.reportFeedbackStatus());
    }

    private void updateFeedbackSchedule(Report report, ReportFeedbackApplication reportFeedbackApplication, UpdateFeedbackScheduleRequestDto requestDto) {
        if (Objects.isNull(requestDto.reportFeedbackStatus())) return;
        if (!ReportFeedbackStatus.PENDING.equals(requestDto.reportFeedbackStatus())) return;
        reportFeedbackApplication.updateSchedule(requestDto);
        createZoomMeeting(report, reportFeedbackApplication, requestDto);
    }

    private void createZoomMeeting(Report report, ReportFeedbackApplication reportFeedbackApplication, UpdateFeedbackScheduleRequestDto requestDto) {
        LocalDateTime feedbackStartDate = reportFeedbackApplication.getCheckedFeedbackDate(requestDto.desiredDateType());
        ZoomMeetingResponseDto zoomMeetingInfo = zoomUtils.createZoomMeeting(report.getTitle(), feedbackStartDate);
        reportFeedbackApplication.setZoomInfo(zoomMeetingInfo);
    }
}
