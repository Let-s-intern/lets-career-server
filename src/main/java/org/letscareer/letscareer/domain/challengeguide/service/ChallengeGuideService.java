package org.letscareer.letscareer.domain.challengeguide.service;

import org.letscareer.letscareer.domain.challengeguide.dto.request.CreateChallengeGuideRequestDto;

public interface ChallengeGuideService {
    void createChallengeGuide(Long challengeId, CreateChallengeGuideRequestDto createChallengeGuideRequestDto);
}
