package org.letscareer.letscareer.domain.curation.repository;

import org.letscareer.letscareer.domain.curation.vo.CurationItemVo;

import java.util.List;

public interface CurationItemQueryRepository {
    List<CurationItemVo> findAllCurationItemVosByCurationId(Long curationId);
}
