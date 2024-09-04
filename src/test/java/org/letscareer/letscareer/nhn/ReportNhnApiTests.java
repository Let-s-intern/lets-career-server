package org.letscareer.letscareer.nhn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.report.dto.req.UpdateReportDocumentRequestDto;
import org.letscareer.letscareer.domain.report.service.impl.UpdateReportDocumentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReportNhnApiTests {
    @Autowired
    private UpdateReportDocumentServiceImpl updateReportDocumentService;

    @Autowired
    private ReportApplicationHelper reportApplicationHelper;

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
}
