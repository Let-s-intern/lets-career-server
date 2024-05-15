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
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.ConflictException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.letscareer.letscareer.domain.application.error.ApplicationErrorCode.CONFLICT_APPLICATION;

@RequiredArgsConstructor
@Transactional
@Service("CHALLENGE")
public class ChallengeApplicationServiceImpl implements ApplicationService {
    private final ChallengeApplicationHelper challengeApplicationHelper;
    private final ChallengeApplicationMapper challengeApplicationMapper;
    private final ChallengeHelper challengeHelper;
    private final PaymentHelper paymentHelper;

    @Override
    public void createApplication(Long programId, User user, CreateApplicationRequestDto createApplicationRequestDto) {
        Challenge challenge = challengeHelper.findChallengeByIdOrThrow(programId);
        validateExistingApplication(challenge.getId(), user.getId());
        ChallengeApplication challengeApplication = challengeApplicationHelper.createChallengeApplicationAndSave(challenge, user);
        Payment payment = paymentHelper.createPaymentAndSave(challengeApplication, createApplicationRequestDto.paymentInfo());
        challengeApplication.setPayment(payment);
    }

    @Override
    public void validateExistingApplication(Long challengeId, Long userId) {
        Optional<ChallengeApplication> challengeApplication = challengeApplicationHelper.findChallengeApplicationByChallengeIdAndUserId(challengeId, userId);
        if(challengeApplication.isPresent()) throw new ConflictException(CONFLICT_APPLICATION);
    }
}
