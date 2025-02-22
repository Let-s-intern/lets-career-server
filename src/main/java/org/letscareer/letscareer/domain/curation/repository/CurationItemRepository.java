package org.letscareer.letscareer.domain.curation.repository;

import org.letscareer.letscareer.domain.curation.entity.CurationItem;
import org.letscareer.letscareer.domain.curation.vo.AdminCurationItemVo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurationItemRepository extends JpaRepository<CurationItem, Long>, CurationItemQueryRepository {
    void deleteAllByCurationId(Long curationId);
    List<AdminCurationItemVo> findAllAdminCurationItemVosByCurationId(Long curationId);
}
