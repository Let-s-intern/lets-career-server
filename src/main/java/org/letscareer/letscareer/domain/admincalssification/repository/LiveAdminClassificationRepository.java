package org.letscareer.letscareer.domain.admincalssification.repository;

import org.letscareer.letscareer.domain.admincalssification.entity.LiveAdminClassification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiveAdminClassificationRepository extends JpaRepository<LiveAdminClassification, Long>, LiveAdminClassificationQueryRepository {
    void deleteAllByLiveId(Long liveId);
}
