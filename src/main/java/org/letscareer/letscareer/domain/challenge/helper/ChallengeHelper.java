package org.letscareer.letscareer.domain.challenge.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.dto.request.CreateChallengeRequestDto;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challenge.error.ChallengeErrorCode;
import org.letscareer.letscareer.domain.challenge.repository.ChallengeRepository;
import org.letscareer.letscareer.domain.challenge.vo.*;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.program.dto.response.ZoomMeetingResponseDto;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ChallengeHelper {
    private final ChallengeRepository challengeRepository;

    public Challenge createChallengeAndSave(CreateChallengeRequestDto challengeRequestDto, ZoomMeetingResponseDto zoomMeetingInfo) {
        Challenge newChallenge = Challenge.createChallenge(challengeRequestDto, zoomMeetingInfo);
        return challengeRepository.save(newChallenge);
    }

    public Challenge findChallengeByIdOrThrow(Long challengeId) {
        return challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException(ChallengeErrorCode.CHALLENGE_NOT_FOUND));
    }

    public ChallengeDetailVo findChallengeDetailByIdOrThrow(Long challengeId) {
        return challengeRepository.findChallengeDetailById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException(ChallengeErrorCode.CHALLENGE_NOT_FOUND));
    }

    public Page<ChallengeProfileVo> findChallengeProfiles(List<ProgramClassification> typeList, List<ProgramStatusType> statusList, Pageable pageable) {
        return challengeRepository.findChallengeProfiles(typeList, statusList, pageable);
    }

    public ChallengeApplicationFormVo findChallengeApplicationFormVoOrThrow(Long challengeId) {
        return challengeRepository.findChallengeApplicationFormVo(challengeId)
                .orElseThrow(() -> new EntityNotFoundException(ChallengeErrorCode.CHALLENGE_NOT_FOUND));
    }

    public ChallengeThumbnailVo findChallengeThumbnailOrThrow(Long challengeId) {
        return challengeRepository.findChallengeThumbnailVo(challengeId)
                .orElseThrow(() -> new EntityNotFoundException(ChallengeErrorCode.CHALLENGE_NOT_FOUND));
    }

    public ChallengeContentVo findChallengeContentOrThrow(Long challengeId) {
        return challengeRepository.findChallengeContentVo(challengeId)
                .orElseThrow(() -> new EntityNotFoundException(ChallengeErrorCode.CHALLENGE_NOT_FOUND));
    }

    public void deleteChallengeById(Long challengeId) {
        challengeRepository.deleteById(challengeId);
    }

}
