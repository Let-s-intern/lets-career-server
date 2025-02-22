package org.letscareer.letscareer.domain.curation.repository;

import org.letscareer.letscareer.domain.curation.type.CurationLocationType;
import org.letscareer.letscareer.domain.curation.vo.AdminCurationDetailVo;
import org.letscareer.letscareer.domain.curation.vo.AdminCurationVo;
import org.letscareer.letscareer.domain.curation.vo.CurationVo;

import java.util.List;
import java.util.Optional;

public interface CurationQueryRepository {
    List<AdminCurationVo> findAdminCurationVosByLocationType(CurationLocationType locationType);
    Optional<AdminCurationDetailVo> findAdminCurationDetailVoById(Long curationId);
    CurationVo findCurationVoByLocationType(CurationLocationType locationType);
}
