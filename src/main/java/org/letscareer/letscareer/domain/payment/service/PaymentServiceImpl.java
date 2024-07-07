package org.letscareer.letscareer.domain.payment.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.application.helper.ApplicationHelper;
import org.letscareer.letscareer.domain.application.helper.LiveApplicationHelper;
import org.letscareer.letscareer.domain.live.vo.LiveEmailVo;
import org.letscareer.letscareer.domain.payment.dto.request.UpdatePaymentRequestDto;
import org.letscareer.letscareer.domain.payment.dto.response.GetPaymentDetailResponseDto;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.helper.PaymentHelper;
import org.letscareer.letscareer.domain.payment.mapper.PaymentMapper;
import org.letscareer.letscareer.domain.payment.vo.PaymentDetailVo;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;
import org.letscareer.letscareer.domain.pg.provider.TossProvider;
import org.letscareer.letscareer.domain.price.helper.ChallengePriceHelper;
import org.letscareer.letscareer.domain.price.helper.LivePriceHelper;
import org.letscareer.letscareer.domain.price.helper.PriceHelper;
import org.letscareer.letscareer.domain.price.vo.PriceDetailVo;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.program.vo.ProgramSimpleVo;
import org.letscareer.letscareer.global.common.utils.email.EmailUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentHelper paymentHelper;
    private final PaymentMapper paymentMapper;
    private final LiveApplicationHelper liveApplicationHelper;
    private final ChallengePriceHelper challengePriceHelper;
    private final ApplicationHelper applicationHelper;
    private final LivePriceHelper livePriceHelper;
    private final TossProvider tossProvider;
    private final EmailUtils emailUtils;

    @Override
    public GetPaymentDetailResponseDto getPaymentDetail(Long paymentId) {
        Payment payment = paymentHelper.findPaymentByIdOrThrow(paymentId);
        Application application = payment.getApplication();
        ProgramSimpleVo programSimpleVo = applicationHelper.findVWApplicationProgramIdByIdOrThrow(application.getId());
        PriceDetailVo priceInfo = finePriceInfoForProgramType(programSimpleVo);
        PaymentDetailVo paymentInfo = paymentHelper.findPaymentDetailVoByPaymentId(paymentId);
        TossPaymentsResponseDto tossInfo = tossProvider.requestPaymentDetail();
        return paymentMapper.toGetPaymentDetailResponseDto(priceInfo, paymentInfo, tossInfo);
    }

    @Override
    public void updatePayment(Long paymentId, ProgramType programType, UpdatePaymentRequestDto updatePaymentRequestDto) {
        Payment payment = paymentHelper.findPaymentByIdOrThrow(paymentId);
        if (isConfirmedEmailTarget(programType, payment, updatePaymentRequestDto)) {
            sendConfirmedEmail(payment);
        }
        payment.updatePayment(updatePaymentRequestDto);
    }

    private PriceDetailVo finePriceInfoForProgramType(ProgramSimpleVo programSimpleVo) {
        if (ProgramType.CHALLENGE.equals(programSimpleVo.programType()))
            return challengePriceHelper.findPriceDetailVoByChallengeId(programSimpleVo.programId());
        else
            return livePriceHelper.findLivePriceDetailVoByLiveId(programSimpleVo.programId());
    }

    private boolean isConfirmedEmailTarget(ProgramType programType,
                                           Payment payment,
                                           UpdatePaymentRequestDto updatePaymentRequestDto) {
        return programType.equals(ProgramType.LIVE)
                && payment.getIsConfirmed().equals(Boolean.FALSE)
                && updatePaymentRequestDto.isConfirmed().equals(Boolean.TRUE);
    }

    private void sendConfirmedEmail(Payment payment) {
        LiveEmailVo liveEmailVo = liveApplicationHelper.findLiveEmailVo(payment.getApplication().getId());
        emailUtils.sendConfirmedEmail(payment.getApplication().getUser().getEmail(), liveEmailVo);
    }
}
