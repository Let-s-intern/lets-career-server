package org.letscareer.letscareer.domain.curation.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.curation.dto.request.CreateCurationItemRequestDto;
import org.letscareer.letscareer.domain.curation.entity.Curation;
import org.letscareer.letscareer.domain.curation.entity.CurationItem;
import org.letscareer.letscareer.domain.curation.repository.CurationItemRepository;
import org.letscareer.letscareer.domain.curation.vo.AdminCurationItemVo;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CurationItemHelper {
    private final CurationItemRepository curationItemRepository;

    public CurationItem createCurationItemAndSave(Curation curation, CreateCurationItemRequestDto requestDto) {
        CurationItem curationItem = CurationItem.createCurationItem(curation, requestDto);
        return curationItemRepository.save(curationItem);
    }

    public void deleteAllCurationItemsByCurationId(Long curationId) {
        curationItemRepository.deleteAllByCurationId(curationId);
    }

    public List<AdminCurationItemVo> findAllAdminCurationItemVosByCurationId(Long curationId) {
        return curationItemRepository.findAllAdminCurationItemVosByCurationId(curationId);
    }
}
