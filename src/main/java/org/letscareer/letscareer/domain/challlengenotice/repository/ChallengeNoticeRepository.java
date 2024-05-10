package org.letscareer.letscareer.domain.challlengenotice.repository;

import org.letscareer.letscareer.domain.challlengenotice.entity.ChallengeNotice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeNoticeRepository extends JpaRepository<ChallengeNotice, Long>, ChallengeNoticeQueryRepository {
}
