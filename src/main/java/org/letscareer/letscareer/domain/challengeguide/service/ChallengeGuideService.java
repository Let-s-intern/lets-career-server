package org.letscareer.letscareer.domain.challengeguide.service;

import org.letscareer.letscareer.domain.challengeguide.dto.request.CreateChallengeGuideRequestDto;
import org.letscareer.letscareer.domain.challengeguide.dto.request.UpdateChallengeGuideRequestDto;

public interface ChallengeGuideService {
    void createChallengeGuide(Long challengeId, CreateChallengeGuideRequestDto createChallengeGuideRequestDto);

    void updateChallengeGuide(Long challengeGuideId, UpdateChallengeGuideRequestDto updateChallengeGuideRequestDto);

    void deleteChallengeGuide(Long challengeGuideId);
}
