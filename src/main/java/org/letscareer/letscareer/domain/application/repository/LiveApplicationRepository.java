package org.letscareer.letscareer.domain.application.repository;

import org.letscareer.letscareer.domain.application.entity.LiveApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiveApplicationRepository extends JpaRepository<LiveApplication, Long>, LiveApplicationQueryRepository {
}
