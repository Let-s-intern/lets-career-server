package org.letscareer.letscareer.domain.challenge.service;

import org.letscareer.letscareer.domain.application.dto.response.GetChallengeApplicationsResponseDto;
import org.letscareer.letscareer.domain.challenge.dto.request.CreateChallengeRequestDto;
import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengeDetailResponseDto;
import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengeReviewResponseDto;
import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengesResponseDto;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChallengeService {
    GetChallengesResponseDto getChallengeList(List<ProgramClassification> typeList, List<ProgramStatusType> statusList, Pageable pageable);

    GetChallengeDetailResponseDto getChallengeDetail(Long challengeId);

    GetChallengeApplicationsResponseDto getApplications(Long challengeId, Boolean isConfirmed);

    GetChallengeReviewResponseDto getReviews(Long challengeId, Pageable pageable);

    void createChallenge(CreateChallengeRequestDto createChallengeRequestDto);

    void updateChallenge(Long challengeId, CreateChallengeRequestDto createChallengeRequestDto);

    void deleteChallenge(Long challengeId);
}
