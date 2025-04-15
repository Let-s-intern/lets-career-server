package org.letscareer.letscareer.domain.challlengenotice.repository;

import org.letscareer.letscareer.domain.challlengenotice.entity.ChallengeNotice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeNoticeRepository extends JpaRepository<ChallengeNotice, Long>, ChallengeNoticeQueryRepository {
    List<ChallengeNotice> findAllByChallengeId(Long challengeId);
    void deleteAllByChallengeId(Long challengeId);
}
