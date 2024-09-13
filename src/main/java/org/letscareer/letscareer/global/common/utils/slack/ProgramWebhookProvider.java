package org.letscareer.letscareer.global.common.utils.slack;

import lombok.RequiredArgsConstructor;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackField;
import net.gpedro.integrations.slack.SlackMessage;
import org.letscareer.letscareer.global.common.utils.slack.dto.ReportWebhookDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ProgramWebhookProvider implements WebhookProvider {
    private static final String FALLBACK = "Ok";
    private static final String COLOR = "danger";
    private static final String MAIN_TITLE = "리포트 신청 알림";
    private static final String REPORT_TITLE = "리포트 종류";
    private static final String REPORT_TYPE = "리포트 타입";
    private static final String USER = "신청자";
    private static final String APPLICATION_TIME = "신청 시간";
    private final SlackApi slackApi;

    @Override
    public void sendMessage(Object sendData) {
        ReportWebhookDto reportWebhookDto = (ReportWebhookDto) sendData;
        List<SlackAttachment> slackAttachments = createBugReportSlackAttachments(reportWebhookDto);
        SlackMessage slackMessage = createSlackMessage(MAIN_TITLE, slackAttachments);
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
                createSlackField(REPORT_TITLE, reportWebhookDto.title()),
                createSlackField(REPORT_TYPE, reportWebhookDto.reportType().getDesc()),
                createSlackField(USER, reportWebhookDto.userName()),
                createSlackField(APPLICATION_TIME, reportWebhookDto.applicationTime().toString())
        );
    }

    private SlackField createSlackField(String title, String value) {
        return new SlackField()
                .setTitle(title)
                .setValue(value);
    }

    private SlackMessage createSlackMessage(String text, List<SlackAttachment> slackAttachments) {
        SlackMessage slackMessage = new SlackMessage();
        slackMessage.setText(text);
        slackMessage.setAttachments(slackAttachments);
        return slackMessage;
    }
}
