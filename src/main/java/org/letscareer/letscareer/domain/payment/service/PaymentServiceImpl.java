package org.letscareer.letscareer.domain.payment.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.application.helper.ApplicationHelper;
import org.letscareer.letscareer.domain.payment.dto.request.UpdatePaymentRequestDto;
import org.letscareer.letscareer.domain.payment.dto.response.GetPaymentDetailResponseDto;
import org.letscareer.letscareer.domain.payment.dto.response.GetPaymentResponseDto;
import org.letscareer.letscareer.domain.payment.dto.response.GetPaymentsResponseDto;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.helper.PaymentHelper;
import org.letscareer.letscareer.domain.payment.mapper.PaymentMapper;
import org.letscareer.letscareer.domain.payment.vo.PaymentDetailVo;
import org.letscareer.letscareer.domain.payment.vo.PaymentProgramVo;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;
import org.letscareer.letscareer.domain.pg.provider.TossProvider;
import org.letscareer.letscareer.domain.price.helper.ChallengePriceHelper;
import org.letscareer.letscareer.domain.price.helper.LivePriceHelper;
import org.letscareer.letscareer.domain.price.vo.PriceDetailVo;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.program.vo.ProgramSimpleVo;
import org.letscareer.letscareer.domain.report.helper.ReportPriceHelper;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentHelper paymentHelper;
    private final PaymentMapper paymentMapper;
    private final ChallengePriceHelper challengePriceHelper;
    private final ApplicationHelper applicationHelper;
    private final LivePriceHelper livePriceHelper;
    private final ReportPriceHelper reportPriceHelper;
    private final TossProvider tossProvider;

    @Override
    public GetPaymentsResponseDto getPayments(User user) {
        List<PaymentProgramVo> paymentProgramInfos = applicationHelper.findPaymentProgramVos(user.getId());
        List<GetPaymentResponseDto> payments = createGetPaymentResponseDto(paymentProgramInfos);
        return paymentMapper.toGetPaymentsResponseDto(payments);
    }

    @Override
    public GetPaymentDetailResponseDto getPaymentDetail(Long paymentId) {
        Payment payment = paymentHelper.findPaymentByIdOrThrow(paymentId);
        Application application = payment.getApplication();
        ProgramSimpleVo programSimpleVo = applicationHelper.findVWApplicationProgramIdByIdOrThrow(application.getId());
        PriceDetailVo priceInfo = findPriceInfoForProgramType(programSimpleVo);
        PaymentDetailVo paymentInfo = paymentHelper.findPaymentDetailVoByPaymentId(paymentId);
        TossPaymentsResponseDto tossInfo = tossProvider.requestPaymentDetail(payment.getPaymentKey());
        return paymentMapper.toGetPaymentDetailResponseDto(programSimpleVo, priceInfo, paymentInfo, tossInfo);
    }

    @Override
    public void updatePayment(Long paymentId, ProgramType programType, UpdatePaymentRequestDto updatePaymentRequestDto) {
        Payment payment = paymentHelper.findPaymentByIdOrThrow(paymentId);
        payment.updatePayment(updatePaymentRequestDto);
    }

    private List<GetPaymentResponseDto> createGetPaymentResponseDto(List<PaymentProgramVo> programInfos) {
        return programInfos.stream()
                .map(programInfo -> paymentMapper.toGetPaymentResponseDto(
                        programInfo,
                        Objects.equals(programInfo.programType(), "CHALLENGE") ? challengePriceHelper.findPriceRefundByChallengeId(programInfo.programId()) : 0,
                        tossProvider.requestPaymentDetail(programInfo.paymentKey())
                ))
                .collect(Collectors.toList());
    }

    private PriceDetailVo findPriceInfoForProgramType(ProgramSimpleVo programSimpleVo) {
        switch(programSimpleVo.programType()) {
            case CHALLENGE: return challengePriceHelper.findPriceDetailVoByChallengeId(programSimpleVo.id());
            case LIVE: return livePriceHelper.findLivePriceDetailVoByLiveId(programSimpleVo.id());
            case REPORT: return reportPriceHelper.findReportPriceDetailVoByReportId(programSimpleVo.id());
        }
        return null;
    }
}
