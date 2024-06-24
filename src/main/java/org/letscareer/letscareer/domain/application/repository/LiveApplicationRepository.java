package org.letscareer.letscareer.domain.application.repository;

import org.letscareer.letscareer.domain.application.entity.LiveApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LiveApplicationRepository extends JpaRepository<LiveApplication, Long>, LiveApplicationQueryRepository {
    Optional<LiveApplication> findLiveApplicationByLiveIdAndUserId(Long liveId, Long userId);

    Long countByLiveId(Long liveId);
}
