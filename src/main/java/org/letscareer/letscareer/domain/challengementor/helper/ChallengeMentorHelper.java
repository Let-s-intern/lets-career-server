package org.letscareer.letscareer.domain.challengementor.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challengementor.entity.ChallengeMentor;
import org.letscareer.letscareer.domain.challengementor.repository.ChallengeMentorRepository;
import org.letscareer.letscareer.domain.challengementor.vo.ChallengeMentorAdminVo;
import org.letscareer.letscareer.domain.challengementor.vo.MyChallengeMentorVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.letscareer.letscareer.domain.challengementor.error.ChallengeMentorErrorCode.CHALLENGE_MENTOR_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class ChallengeMentorHelper {
    private final ChallengeMentorRepository challengeMentorRepository;

    public void createChallengeMentorAndSave(Challenge challenge, User mentor) {
        ChallengeMentor challengeMentor = ChallengeMentor.createChallengeMentor(challenge, mentor);
        challengeMentorRepository.save(challengeMentor);
    }

    public boolean existsByChallengeAndMentor(Challenge challenge, User mentor) {
        return challengeMentorRepository.existsByChallengeAndMentor(challenge, mentor);
    }

    public ChallengeMentor findChallengeMentorByIdOrElseThrow(Long challengeMentorId) {
        return challengeMentorRepository.findById(challengeMentorId).orElseThrow(() -> new EntityNotFoundException(CHALLENGE_MENTOR_NOT_FOUND));
    }

    public void deleteChallengeMentorById(Long challengeMentorId) {
        challengeMentorRepository.deleteById(challengeMentorId);
    }

    public List<ChallengeMentorAdminVo> findChallengeMentorAdminVosByChallengeId(Long challengeId) {
        return challengeMentorRepository.findChallengeMentorAdminVosByChallengeId(challengeId);
    }

    public List<MyChallengeMentorVo> findAllMyChallengeMentorVosByMentorId(Long mentorId) {
        return challengeMentorRepository.findAllMyChallengeMentorVosByMentorId(mentorId);
    }
}
