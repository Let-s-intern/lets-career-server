package org.letscareer.letscareer.domain.score.repository;

import org.letscareer.letscareer.domain.score.entity.AdminScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminScoreRepository extends JpaRepository<AdminScore, Long>, AdminScoreQueryRepository {
}
