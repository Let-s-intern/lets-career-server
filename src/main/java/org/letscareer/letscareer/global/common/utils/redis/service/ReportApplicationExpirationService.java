package org.letscareer.letscareer.global.common.utils.redis.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.nhn.dto.request.report.ReportIngParameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReportApplicationExpirationService extends ExpirationService<ReportIngParameter> {

    @Value("${spring.data.redis.report-application.key}")
    private String reportApplicationKey;
    @Value("${spring.data.redis.report-application.expire}")
    private int expirationTime;

    @Override
    public void saveWithExpire(ReportIngParameter reportIngParameter) {
        // ::TODO Redis에 대상 ReportApplication 정보 저장
    }
}
