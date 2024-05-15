package org.letscareer.letscareer.domain.application.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.ChallengeApplication;
import org.letscareer.letscareer.domain.application.repository.ChallengeApplicationRepository;
import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationVo;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.ConflictException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.application.error.ApplicationErrorCode.CONFLICT_APPLICATION;

@RequiredArgsConstructor
@Transactional
@Component
public class ChallengeApplicationHelper {
    private final ChallengeApplicationRepository challengeApplicationRepository;

    public List<AdminChallengeApplicationVo> findAdminChallengeApplicationVos(Long challengeId, Boolean isConfirmed) {
        return challengeApplicationRepository.findAdminChallengeApplicationVos(challengeId, isConfirmed);
    }

    public ChallengeApplication createChallengeApplicationAndSave(Challenge challenge, User user) {
        ChallengeApplication newChallengeApplication = ChallengeApplication.createChallengeApplication(challenge, user);
        return challengeApplicationRepository.save(newChallengeApplication);
    }

    public void validateExistingApplication(Long challengeId, Long userId) {
        Optional<ChallengeApplication> challengeApplication = challengeApplicationRepository.findChallengeApplicationByChallengeIdAndUserId(challengeId, userId);
        if(challengeApplication.isPresent()) throw new ConflictException(CONFLICT_APPLICATION);
    }
}
