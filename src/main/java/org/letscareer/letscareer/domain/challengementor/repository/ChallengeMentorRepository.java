package org.letscareer.letscareer.domain.challengementor.repository;

import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challengementor.entity.ChallengeMentor;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeMentorRepository extends JpaRepository<ChallengeMentor, Long>, ChallengeMentorQueryRepository {
    boolean existsByChallengeAndMentor(Challenge challenge, User mentor);
}
