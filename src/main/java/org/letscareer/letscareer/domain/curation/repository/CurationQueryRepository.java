package org.letscareer.letscareer.domain.curation.repository;

import org.letscareer.letscareer.domain.curation.type.CurationLocationType;
import org.letscareer.letscareer.domain.curation.vo.AdminCurationDetailVo;
import org.letscareer.letscareer.domain.curation.vo.CurationAdminVo;

import java.util.List;
import java.util.Optional;

public interface CurationQueryRepository {
    List<CurationAdminVo> findCurationAdminVosByLocationType(CurationLocationType locationType);
    Optional<AdminCurationDetailVo> findAdminCurationDetailVoById(Long curationId);
}
