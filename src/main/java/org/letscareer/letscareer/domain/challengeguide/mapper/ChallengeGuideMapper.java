package org.letscareer.letscareer.domain.challengeguide.mapper;

import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challengeguide.dto.request.CreateChallengeGuideRequestDto;
import org.letscareer.letscareer.domain.challengeguide.entity.ChallengeGuide;
import org.springframework.stereotype.Component;

@Component
public class ChallengeGuideMapper {
    public ChallengeGuide toEntity(Challenge challenge, CreateChallengeGuideRequestDto createChallengeGuideRequestDto) {
        return ChallengeGuide.of(challenge, createChallengeGuideRequestDto);
    }
}
