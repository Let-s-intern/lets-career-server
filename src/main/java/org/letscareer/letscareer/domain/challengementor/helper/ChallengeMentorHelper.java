package org.letscareer.letscareer.domain.challengementor.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challengementor.entity.ChallengeMentor;
import org.letscareer.letscareer.domain.challengementor.repository.ChallengeMentorRepository;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

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
}
