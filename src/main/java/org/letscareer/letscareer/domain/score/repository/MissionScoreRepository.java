package org.letscareer.letscareer.domain.score.repository;

import org.letscareer.letscareer.domain.score.entity.MissionScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionScoreRepository extends JpaRepository<MissionScore, Long> {
}
