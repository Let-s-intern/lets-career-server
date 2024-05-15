package org.letscareer.letscareer.domain.application.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.request.CreateApplicationRequestDto;
import org.letscareer.letscareer.domain.application.entity.LiveApplication;
import org.letscareer.letscareer.domain.application.helper.LiveApplicationHelper;
import org.letscareer.letscareer.domain.application.mapper.LiveApplicationMapper;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.live.helper.LiveHelper;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.service.PaymentService;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.InvalidValueException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.letscareer.letscareer.domain.application.error.ApplicationErrorCode.LIVE_BAD_REQUEST;

@RequiredArgsConstructor
@Transactional
@Service("LIVE")
public class LiveApplicationServiceImpl implements ApplicationService {
    private final LiveApplicationHelper liveApplicationHelper;
    private final LiveApplicationMapper liveApplicationMapper;
    private final LiveHelper liveHelper;
    private final PaymentService paymentService;

    @Override
    public void createApplication(Long programId, User user, CreateApplicationRequestDto createApplicationRequestDto) {
        Live live = liveHelper.findLiveByIdOrThrow(programId);
        liveApplicationHelper.validateExistingApplication(live.getId(), user.getId());
        validateCreateLiveApplicationDto(createApplicationRequestDto);
        LiveApplication liveApplication = liveApplicationHelper.createLiveApplicationAndSave(createApplicationRequestDto, live, user);
        Payment payment = paymentService.createPaymentAndSave(liveApplication, createApplicationRequestDto.paymentInfo());
        liveApplication.setPayment(payment);
    }

    private void validateCreateLiveApplicationDto(CreateApplicationRequestDto createApplicationRequestDto) {
        if(createApplicationRequestDto.motivate().isBlank())
            throw new InvalidValueException(LIVE_BAD_REQUEST);
    }
}
