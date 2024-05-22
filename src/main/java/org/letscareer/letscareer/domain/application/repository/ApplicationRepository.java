package org.letscareer.letscareer.domain.application.repository;

import org.letscareer.letscareer.domain.application.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long>, ApplicationQueryRepository {
}
