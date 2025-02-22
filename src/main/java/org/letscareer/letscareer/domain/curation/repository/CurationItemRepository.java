package org.letscareer.letscareer.domain.curation.repository;

import org.letscareer.letscareer.domain.curation.entity.CurationItem;
import org.letscareer.letscareer.domain.curation.vo.CurationItemVo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurationItemRepository extends JpaRepository<CurationItem, Long>, CurationQueryRepository {
    void deleteAllByCurationId(Long curationId);
    List<CurationItemVo> findAllCurationItemVoByCurationId(Long curationId);
}
