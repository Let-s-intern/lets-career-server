package org.letscareer.letscareer.domain.curation.service;

import org.letscareer.letscareer.domain.curation.dto.request.CreateCurationRequestDto;
import org.letscareer.letscareer.domain.curation.dto.request.UpdateCurationRequestDto;
import org.letscareer.letscareer.domain.curation.dto.response.GetAdminCurationResponseDto;
import org.letscareer.letscareer.domain.curation.dto.response.GetAdminCurationsResponseDto;
import org.letscareer.letscareer.domain.curation.dto.response.GetCurationResponseDto;
import org.letscareer.letscareer.domain.curation.type.CurationLocationType;

public interface CurationService {
    GetAdminCurationsResponseDto getAdminCurations(CurationLocationType locationType);
    GetAdminCurationResponseDto getAdminCuration(Long curationId);
    GetCurationResponseDto getCuration(CurationLocationType locationType);
    void createCuration(CurationLocationType locationType, CreateCurationRequestDto requestDto);
    void updateCuration(Long curationId, UpdateCurationRequestDto requestDto);
    void deleteCuration(Long curationId);
}
