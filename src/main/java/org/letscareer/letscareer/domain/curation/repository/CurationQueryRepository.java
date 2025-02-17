package org.letscareer.letscareer.domain.curation.repository;

import org.letscareer.letscareer.domain.curation.type.CurationLocationType;
import org.letscareer.letscareer.domain.curation.vo.CurationAdminVo;

import java.util.List;

public interface CurationQueryRepository {
    List<CurationAdminVo> findCurationAdminVosByLocationType(CurationLocationType locationType);
}
