package org.letscareer.letscareer.domain.curation.service;

import org.letscareer.letscareer.domain.curation.dto.request.CreateCurationRequestDto;
import org.letscareer.letscareer.domain.curation.dto.request.UpdateCurationRequestDto;
import org.letscareer.letscareer.domain.curation.type.CurationLocationType;

public interface CurationService {
    void createCuration(CurationLocationType locationType, CreateCurationRequestDto requestDto);
    void updateCuration(Long curationId, UpdateCurationRequestDto requestDto);
}
