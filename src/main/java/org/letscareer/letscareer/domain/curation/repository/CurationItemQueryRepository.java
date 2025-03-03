package org.letscareer.letscareer.domain.curation.repository;

import org.letscareer.letscareer.domain.curation.vo.AdminCurationItemVo;
import org.letscareer.letscareer.domain.curation.vo.CurationItemVo;

import java.util.List;

public interface CurationItemQueryRepository {
    List<AdminCurationItemVo> findAllAdminCurationItemVosByCurationId(Long curationId);
    List<CurationItemVo> findAllCurationItemVosByCurationId(Long curationId);
}
