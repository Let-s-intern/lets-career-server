package org.letscareer.letscareer.global.common.utils.slack;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackField;
import net.gpedro.integrations.slack.SlackMessage;
import org.letscareer.letscareer.global.common.utils.slack.dto.ReportWebhookDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class ProgramWebhookProvider implements WebhookProvider {
    private static final String FALLBACK = "Ok";
    private static final String COLOR = "danger";
    private static final String MAIN_TITLE = "리포트 신청 알림";
    private static final String REPORT_TITLE = "리포트 종류";
    private static final String PRICE_TYPE = "기본 서비스 유형";
    private static final String OPTIONS = "서류 진단서 옵션";
    private static final String IS_APPLIED_FEEDBACK = "1:1 피드백 신청여부";
    private static final String ORDER_ID = "주문번호";
    private static final String USER_INFO = "신청자 / 이메일 / 전화번호";
    private static final String TIME_INFO = "신청일자 / 진단서 업로드 마감일자 (베이직 : +2일, 프리미엄 : +3일, 옵션추가시 : +5일)";
    private final SlackApi slackApi;

    @Override
    public void sendMessage(Object sendData) {
        ReportWebhookDto reportWebhookDto = (ReportWebhookDto) sendData;
        List<SlackAttachment> slackAttachments = createBugReportSlackAttachments(reportWebhookDto);
        SlackMessage slackMessage = createSlackMessage(slackAttachments);
        slackApi.call(slackMessage);
    }

    private List<SlackAttachment> createBugReportSlackAttachments(ReportWebhookDto reportWebhookDto) {
        SlackAttachment slackAttachment = new SlackAttachment();
        List<SlackField> slackFields = createBugReportSlackFields(reportWebhookDto);
        slackAttachment.setFallback(FALLBACK);
        slackAttachment.setColor(COLOR);
        slackAttachment.setFields(slackFields);
        return Collections.singletonList(slackAttachment);
    }

    private List<SlackField> createBugReportSlackFields(ReportWebhookDto reportWebhookDto) {
        return List.of(
                createSlackField(REPORT_TITLE, reportWebhookDto.getReportTitle()),
                createSlackField(PRICE_TYPE, reportWebhookDto.getReportPriceType()),
                createSlackField(OPTIONS, reportWebhookDto.getOptionsString()),
                createSlackField(IS_APPLIED_FEEDBACK, reportWebhookDto.isAppliedFeedback()),
                createSlackField(ORDER_ID, reportWebhookDto.getOrderId()),
                createSlackField(USER_INFO, reportWebhookDto.getUserInfo()),
                createSlackField(TIME_INFO, reportWebhookDto.getTimeInfo())
        );
    }

    private SlackField createSlackField(String title, String value) {
        return new SlackField()
                .setTitle(title)
                .setValue(value);
    }

    private SlackMessage createSlackMessage(List<SlackAttachment> slackAttachments) {
        SlackMessage slackMessage = new SlackMessage();
        slackMessage.setText(ProgramWebhookProvider.MAIN_TITLE);
        slackMessage.setAttachments(slackAttachments);
        return slackMessage;
    }
}
