package org.letscareer.letscareer.domain.curation.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.curation.dto.request.CreateCurationRequestDto;
import org.letscareer.letscareer.domain.curation.entity.Curation;
import org.letscareer.letscareer.domain.curation.repository.CurationRepository;
import org.letscareer.letscareer.domain.curation.type.CurationLocationType;
import org.letscareer.letscareer.domain.curation.vo.CurationAdminVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.letscareer.letscareer.domain.curation.error.CurationErrorCode.CURATION_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class CurationHelper {
    private final CurationRepository curationRepository;

    public List<CurationAdminVo> findCurationAdminVosByLocationType(CurationLocationType locationType) {
        return curationRepository.findCurationAdminVosByLocationType(locationType);
    }

    public void createCurationAndSave(CurationLocationType locationType, CreateCurationRequestDto requestDto) {
        Curation curation = Curation.createCuration(locationType, requestDto);
        curationRepository.save(curation);
    }

    public Curation findCurationByIdOrThrow(Long curationId) {
        return curationRepository.findById(curationId).orElseThrow(() -> new EntityNotFoundException(CURATION_NOT_FOUND));
    }

    public void deleteCuration(Curation curation) {
        curationRepository.delete(curation);
    }
}
