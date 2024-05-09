package org.letscareer.letscareer.domain.challenge.service;

import org.letscareer.letscareer.domain.challenge.dto.request.CreateChallengeRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface ChallengeService {
    void createChallenge(CreateChallengeRequestDto createChallengeRequestDto);
}
