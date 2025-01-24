package org.letscareer.letscareer.domain.admincalssification.repository;

import org.letscareer.letscareer.domain.admincalssification.entity.VodAdminClassification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VodAdminClassificationRepository extends JpaRepository<VodAdminClassification, Long>, VodAdminClassificationQueryRepository {
    void deleteAllByVodId(Long vodId);
}
