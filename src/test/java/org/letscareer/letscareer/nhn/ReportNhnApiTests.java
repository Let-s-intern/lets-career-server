package org.letscareer.letscareer.nhn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.nhn.dto.request.report.ReportRefundParameter;
import org.letscareer.letscareer.domain.report.dto.req.UpdateReportDocumentRequestDto;
import org.letscareer.letscareer.domain.report.service.impl.CancelReportApplicationServiceImpl;
import org.letscareer.letscareer.domain.report.service.impl.UpdateReportDocumentServiceImpl;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.helper.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReportNhnApiTests {
    @Autowired
    private UpdateReportDocumentServiceImpl updateReportDocumentService;
    @Autowired
    private CancelReportApplicationServiceImpl cancelReportApplicationService;
    @Autowired
    private ReportApplicationHelper reportApplicationHelper;
    @Autowired
    private UserHelper userHelper;

    @Test
    @DisplayName("진단서 진단완료 알림톡")
    void reportDoneNotification() {
        // given
        UpdateReportDocumentRequestDto requestDto = new UpdateReportDocumentRequestDto("https://www.google.com");
        Long applicationId = 255L;

        // when
        updateReportDocumentService.execute(applicationId, requestDto);

        // then
        ReportApplication reportApplication = reportApplicationHelper.findReportApplicationByReportApplicationIdOrThrow(applicationId);
        assertThat(reportApplication.getReportUrl()).isEqualTo("https://www.google.com");
    }

    @Test
    @DisplayName("서류 진단 신청서 환불 알림톡")
    void reportRefundNotification() {
        // given
        User user = userHelper.findUserByIdOrThrow(26L);
        Long applicationId = 262L;

        // when
        cancelReportApplicationService.execute(user, applicationId);

        // then
        ReportApplication reportApplication = reportApplicationHelper.findReportApplicationByReportApplicationIdOrThrow(applicationId);
        assertThat(reportApplication.getIsCanceled()).isEqualTo(true);
    }
}
