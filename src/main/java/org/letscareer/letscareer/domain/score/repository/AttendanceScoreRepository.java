package org.letscareer.letscareer.domain.score.repository;

import org.letscareer.letscareer.domain.score.entity.AttendanceScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttendanceScoreRepository extends JpaRepository<AttendanceScore, Long>, AttendanceScoreQueryRepository {
}
