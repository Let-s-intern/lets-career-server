package org.letscareer.letscareer.domain.challengeguide.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challenge.helper.ChallengeHelper;
import org.letscareer.letscareer.domain.challengeguide.dto.request.CreateChallengeGuideRequestDto;
import org.letscareer.letscareer.domain.challengeguide.entity.ChallengeGuide;
import org.letscareer.letscareer.domain.challengeguide.helper.ChallengeGuideHelper;
import org.letscareer.letscareer.domain.challengeguide.mapper.ChallengeGuideMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ChallengeGuideServiceImpl implements ChallengeGuideService {
    private final ChallengeGuideHelper challengeGuideHelper;
    private final ChallengeGuideMapper challengeGuideMapper;
    private final ChallengeHelper challengeHelper;

    @Override
    public void createChallengeGuide(Long challengeId, CreateChallengeGuideRequestDto createChallengeGuideRequestDto) {
        Challenge challenge = challengeHelper.findChallengeByIdOrThrow(challengeId);
        ChallengeGuide newChallengeGuide = challengeGuideMapper.toEntity(challenge, createChallengeGuideRequestDto);
        challengeGuideHelper.saveChallengeGuide(newChallengeGuide);
    }
}
