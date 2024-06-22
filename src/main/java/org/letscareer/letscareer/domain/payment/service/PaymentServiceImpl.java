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
import org.letscareer.letscareer.domain.price.entity.Price;
import org.letscareer.letscareer.domain.price.helper.ChallengePriceHelper;
import org.letscareer.letscareer.domain.price.helper.LivePriceHelper;
import org.letscareer.letscareer.domain.price.helper.PriceHelper;
import org.letscareer.letscareer.domain.price.vo.PriceDetailVo;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.program.vo.ProgramSimpleVo;
import org.letscareer.letscareer.global.common.utils.EmailUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentHelper paymentHelper;
    private final PaymentMapper paymentMapper;
    private final ApplicationHelper applicationHelper;
    private final PriceHelper priceHelper;
    private final ChallengePriceHelper challengePriceHelper;
    private final LivePriceHelper livePriceHelper;
    private final LiveApplicationHelper liveApplicationHelper;
    private final EmailUtils emailUtils;

    @Override
    public GetPaymentDetailResponseDto getPaymentDetail(Long paymentId) {
        Payment payment = paymentHelper.findPaymentByIdOrThrow(paymentId);
        Application application = payment.getApplication();
        ProgramSimpleVo programSimpleVo = applicationHelper.findVWApplicationProgramIdByIdOrThrow(application.getId());
        PriceDetailVo priceInfo = null;
        switch (programSimpleVo.programType()) {
            case CHALLENGE -> {
                priceInfo = challengePriceHelper.findPriceDetailVoByChallengeId(programSimpleVo.programId());
            }
            case LIVE -> {
                priceInfo = livePriceHelper.findLivePriceDetailVoByLiveId(programSimpleVo.programId());
            }
        }
        PaymentDetailVo paymentInfo = paymentHelper.findPaymentDetailVoByPaymentId(paymentId);
        return paymentMapper.toGetPaymentDetailResponseDto(priceInfo, paymentInfo);
    }

    @Override
    public void updatePayment(Long paymentId, ProgramType programType, UpdatePaymentRequestDto updatePaymentRequestDto) {
        Payment payment = paymentHelper.findPaymentByIdOrThrow(paymentId);
        if(isConfirmedEmailTarget(programType, payment, updatePaymentRequestDto)) {
            sendConfirmedEmail(payment);
        }
        payment.updatePayment(updatePaymentRequestDto);
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
