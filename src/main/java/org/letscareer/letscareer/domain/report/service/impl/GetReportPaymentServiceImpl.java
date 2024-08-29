package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.helper.PaymentHelper;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;
import org.letscareer.letscareer.domain.pg.provider.TossProvider;
import org.letscareer.letscareer.domain.report.dto.res.GetReportPaymentResponseDto;
import org.letscareer.letscareer.domain.report.helper.ReportHelper;
import org.letscareer.letscareer.domain.report.mapper.ReportMapper;
import org.letscareer.letscareer.domain.report.service.GetReportPaymentService;
import org.letscareer.letscareer.domain.report.vo.ReportApplicationVo;
import org.letscareer.letscareer.domain.report.vo.ReportPaymentVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class GetReportPaymentServiceImpl implements GetReportPaymentService {
    private final ReportHelper reportHelper;
    private final ReportMapper reportMapper;
    private final PaymentHelper paymentHelper;
    private final TossProvider tossProvider;

    @Override
    public GetReportPaymentResponseDto execute(User user, Long applicationId) {
        Payment payment = paymentHelper.findPaymentByApplicationIdOrThrow(applicationId);
        ReportApplicationVo reportApplicationInfo = reportHelper.findReportApplicationVoByApplicationId(applicationId);
        ReportPaymentVo reportPaymentInfo = reportHelper.findReportPaymentVoByApplicationId(applicationId);
        TossPaymentsResponseDto tossInfo = tossProvider.requestPaymentDetail(payment.getPaymentKey());
        return reportMapper.toGetReportPaymentResponseDto(reportApplicationInfo, reportPaymentInfo, tossInfo);
    }
}
