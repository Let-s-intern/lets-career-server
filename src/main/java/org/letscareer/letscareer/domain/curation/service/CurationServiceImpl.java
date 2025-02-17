package org.letscareer.letscareer.domain.curation.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.curation.dto.request.CreateCurationRequestDto;
import org.letscareer.letscareer.domain.curation.dto.request.UpdateCurationRequestDto;
import org.letscareer.letscareer.domain.curation.entity.Curation;
import org.letscareer.letscareer.domain.curation.helper.CurationHelper;
import org.letscareer.letscareer.domain.curation.type.CurationLocationType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CurationServiceImpl implements CurationService {
    private final CurationHelper curationHelper;

    @Override
    public void createCuration(CurationLocationType locationType, CreateCurationRequestDto requestDto) {
        curationHelper.createCurationAndSave(locationType, requestDto);
    }

    @Override
    public void updateCuration(Long curationId, UpdateCurationRequestDto requestDto) {
        Curation curation = curationHelper.findCurationByIdOrThrow(curationId);
        curation.updateCuration(requestDto);
    }
}
