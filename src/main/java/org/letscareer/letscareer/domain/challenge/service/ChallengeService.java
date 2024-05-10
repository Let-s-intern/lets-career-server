package org.letscareer.letscareer.domain.challenge.service;

import org.letscareer.letscareer.domain.application.dto.response.GetChallengeApplicationsResponseDto;
import org.letscareer.letscareer.domain.challenge.dto.request.CreateChallengeRequestDto;
import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengeDetailResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface ChallengeService {
    GetChallengeDetailResponseDto getChallengeDetail(Long challengeId);

    GetChallengeApplicationsResponseDto getApplications(Long challengeId, Boolean isConfirmed);

    void createChallenge(CreateChallengeRequestDto createChallengeRequestDto);

    void updateChallenge(Long challengeId, CreateChallengeRequestDto createChallengeRequestDto);

    void deleteChallenge(Long challengeId);
}
