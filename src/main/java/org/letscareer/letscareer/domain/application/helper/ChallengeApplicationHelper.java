package org.letscareer.letscareer.domain.application.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.ChallengeApplication;
import org.letscareer.letscareer.domain.application.repository.ChallengeApplicationRepository;
import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationVo;
import org.letscareer.letscareer.domain.application.vo.UserChallengeApplicationVo;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.type.UserRole;
import org.letscareer.letscareer.global.error.exception.ConflictException;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.letscareer.letscareer.global.error.exception.InvalidValueException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.letscareer.letscareer.domain.application.error.ApplicationErrorCode.*;

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
        if (challengeApplication.isPresent())
            throw new ConflictException(CONFLICT_APPLICATION);
    }

    public void validateChallengeDuration(Challenge challenge) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(challenge.getDeadline()))
            throw new InvalidValueException(INVALID_APPLICATION_TIME);
    }

    public List<AdminChallengeApplicationVo> findAdminChallengeApplicationVos(Long challengeId, Boolean isConfirmed) {
        return challengeApplicationRepository.findAdminChallengeApplicationVos(challengeId, isConfirmed);
    }

    public ChallengeApplication findChallengeApplicationByIdOrThrow(Long applicationId) {
        return challengeApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException(APPLICATION_NOT_FOUND));
    }

    public Page<UserChallengeApplicationVo> findUserChallengeApplicationVo(Long challengeId, Pageable pageable) {
        return challengeApplicationRepository.findUserChallengeApplicationVo(challengeId, pageable);
    }

    public Long countChallengeApplications(Long challengeId) {
        return challengeApplicationRepository.countByChallengeId(challengeId);
    }

    public void deleteChallengeApplication(ChallengeApplication challengeApplication) {
        challengeApplicationRepository.delete(challengeApplication);
    }

    public Boolean checkExistingChallengeApplication(User user, Long challengeId) {
        if (Objects.isNull(user)) return null;
        return challengeApplicationRepository.findChallengeApplicationIdByUserIdAndChallengeId(user.getId(), challengeId).isPresent();
    }

    public void validateChallengeDashboardAccessibleUser(Long challengeId, User user) {
        if (user.getRole().equals(UserRole.ADMIN)) return;
        challengeApplicationRepository.findChallengeApplicationIdByChallengeIdAndUserIdAndIsConfirmed(challengeId, user.getId(), true)
                .orElseThrow(() -> new EntityNotFoundException(APPLICATION_NOT_FOUND));
    }
}
