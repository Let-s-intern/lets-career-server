package org.letscareer.letscareer.domain.challenge.repository;

import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long>, ChallengeQueryRepository {
}
