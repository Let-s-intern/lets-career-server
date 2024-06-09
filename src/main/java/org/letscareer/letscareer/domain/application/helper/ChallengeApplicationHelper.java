package org.letscareer.letscareer.domain.application.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.ChallengeApplication;
import org.letscareer.letscareer.domain.application.repository.ChallengeApplicationRepository;
import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationVo;
import org.letscareer.letscareer.domain.application.vo.UserChallengeApplicationVo;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.ConflictException;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.application.error.ApplicationErrorCode.APPLICATION_NOT_FOUND;
import static org.letscareer.letscareer.domain.application.error.ApplicationErrorCode.CONFLICT_APPLICATION;

@RequiredArgsConstructor
@Component
public class ChallengeApplicationHelper {
    private final ChallengeApplicationRepository challengeApplicationRepository;

    public ChallengeApplication createChallengeApplicationAndSave(Challenge challenge, User user) {
        ChallengeApplication newChallengeApplication = ChallengeApplication.createChallengeApplication(challenge, user);
        return challengeApplicationRepository.save(newChallengeApplication);
    }

    public void validateExistingApplication(Long challengeId, Long userId) {
        Optional<ChallengeApplication> challengeApplication = challengeApplicationRepository.findChallengeApplicationByChallengeIdAndUserId(challengeId, userId);
        if (challengeApplication.isPresent()) throw new ConflictException(CONFLICT_APPLICATION);
    }

    public List<AdminChallengeApplicationVo> findAdminChallengeApplicationVos(Long challengeId, Boolean isConfirmed) {
        return challengeApplicationRepository.findAdminChallengeApplicationVos(challengeId, isConfirmed);
    }

    public ChallengeApplication findChallengeApplicationByIdOrThrow(Long applicationId) {
        return challengeApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException(APPLICATION_NOT_FOUND));
    }

    public List<UserChallengeApplicationVo> findUserChallengeApplicationVo(Long challengeId) {
        return challengeApplicationRepository.findUserChallengeApplicationVo(challengeId);
    }

    public void deleteChallengeApplication(ChallengeApplication challengeApplication) {
        challengeApplicationRepository.delete(challengeApplication);
    }

    public Boolean checkExistingChallengeApplication(Long userId, Long challengeId) {
        return challengeApplicationRepository.findChallengeApplicationIdByUserIdAndChallengeId(userId, challengeId).isPresent();
    }
}
