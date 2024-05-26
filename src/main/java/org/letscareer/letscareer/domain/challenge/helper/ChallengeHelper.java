package org.letscareer.letscareer.domain.challenge.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.dto.request.CreateChallengeRequestDto;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challenge.error.ChallengeErrorCode;
import org.letscareer.letscareer.domain.challenge.repository.ChallengeRepository;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeDetailVo;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeProfileVo;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
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

    public Challenge createChallengeAndSave(CreateChallengeRequestDto challengeRequestDto) {
        Challenge newChallenge = Challenge.createChallenge(challengeRequestDto);
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

    public void deleteChallengeById(Long challengeId) {
        challengeRepository.deleteById(challengeId);
    }

}
