package org.letscareer.letscareer.domain.challenge.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.dto.request.CreateChallengeRequestDto;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challenge.repository.ChallengeRepository;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class ChallengeHelper {
    private final ChallengeRepository challengeRepository;

    public Challenge createChallengeAndSave(CreateChallengeRequestDto challengeRequestDto) {
        Challenge newChallenge = Challenge.createChallenge(challengeRequestDto);
        return challengeRepository.save(newChallenge);
    }

    public Challenge findChallengeByIdOrThrow(Long challengeId) {
        return challengeRepository.findById(challengeId).orElseThrow(EntityNotFoundException::new);
    }
}
