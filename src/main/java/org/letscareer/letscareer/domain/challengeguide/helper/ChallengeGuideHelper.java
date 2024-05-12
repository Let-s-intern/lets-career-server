package org.letscareer.letscareer.domain.challengeguide.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challengeguide.entity.ChallengeGuide;
import org.letscareer.letscareer.domain.challengeguide.repository.ChallengeGuideRepository;
import org.letscareer.letscareer.domain.challengeguide.vo.ChallengeGuideAdminVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.letscareer.letscareer.domain.challengeguide.error.ChallengeGuideErrorCode.CHALLENGE_GUIDE_NOT_FOUND;

@RequiredArgsConstructor
@Transactional
@Component
public class ChallengeGuideHelper {
    private final ChallengeGuideRepository challengeGuideRepository;

    public void saveChallengeGuide(ChallengeGuide challengeGuide) {
        challengeGuideRepository.save(challengeGuide);
    }

    public List<ChallengeGuideAdminVo> findAllChallengeGuideAdminVos(Long challengeId) {
        return challengeGuideRepository.findAllChallengeGuideAdminVos(challengeId);
    }

    public ChallengeGuide findChallengeGuideByIdOrThrow(Long challengeGuideId) {
        return challengeGuideRepository.findById(challengeGuideId).orElseThrow(() -> new EntityNotFoundException(CHALLENGE_GUIDE_NOT_FOUND));
    }
}
