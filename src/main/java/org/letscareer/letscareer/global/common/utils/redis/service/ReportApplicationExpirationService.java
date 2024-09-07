package org.letscareer.letscareer.global.common.utils.redis.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.nhn.dto.request.report.ReportIngParameter;
import org.letscareer.letscareer.domain.nhn.provider.NhnProvider;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.helper.ReportOptionHelper;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Transactional
@Service
public class ReportApplicationExpirationService extends ExpirationService<Long> {
    private final RedisTemplate<String, String> redisTemplate;
    private final ReportApplicationHelper reportApplicationHelper;
    private final ReportOptionHelper reportOptionHelper;
    private final NhnProvider nhnProvider;

    @Value("${spring.data.redis.report-application.key}")
    private String reportApplicationKey;
    @Value("${spring.data.redis.report-application.expire}")
    private int expirationHour;

    @Override
    public void setWithExpire(Long reportApplicationId) {
        String key = reportApplicationKey + reportApplicationId;
        redisTemplate.opsForValue().set(key, String.valueOf(reportApplicationId), expirationHour, TimeUnit.HOURS);
    }

    public void sendKakaoMessage(Long reportApplicationId) {
        ReportApplication reportApplication = reportApplicationHelper.findReportApplicationByReportApplicationIdOrThrow(reportApplicationId);
        User user = reportApplication.getUser();
        Report report = reportApplication.getReport();
        String reportOptionListStr = reportOptionHelper.createReportOptionListStr(reportApplication.getReportApplicationOptionList());
        ReportIngParameter reportIngParameter = ReportIngParameter.of(user.getName(), report.getTitle(), report.getType().getDesc(), reportOptionListStr);
        nhnProvider.sendKakaoMessage(user, reportIngParameter, "report_ing");
    }
}
