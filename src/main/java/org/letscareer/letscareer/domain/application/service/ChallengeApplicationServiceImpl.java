package org.letscareer.letscareer.domain.application.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.request.CreateApplicationRequestDto;
import org.letscareer.letscareer.domain.application.entity.ChallengeApplication;
import org.letscareer.letscareer.domain.application.helper.ChallengeApplicationHelper;
import org.letscareer.letscareer.domain.application.mapper.ChallengeApplicationMapper;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challenge.helper.ChallengeHelper;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.helper.PaymentHelper;
import org.letscareer.letscareer.domain.payment.service.PaymentService;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service("CHALLENGE")
public class ChallengeApplicationServiceImpl implements ApplicationService {
    private final ChallengeApplicationHelper challengeApplicationHelper;
    private final ChallengeApplicationMapper challengeApplicationMapper;
    private final ChallengeHelper challengeHelper;
    private final PaymentService paymentService;

    @Override
    public void createApplication(Long programId, User user, CreateApplicationRequestDto createApplicationRequestDto) {
        Challenge challenge = challengeHelper.findChallengeByIdOrThrow(programId);
        challengeApplicationHelper.validateExistingApplication(challenge.getId(), user.getId());
        ChallengeApplication challengeApplication = challengeApplicationHelper.createChallengeApplicationAndSave(challenge, user);
        Payment payment = paymentService.createPaymentAndSave(challengeApplication, createApplicationRequestDto.paymentInfo());
        challengeApplication.setPayment(payment);
    }
}
