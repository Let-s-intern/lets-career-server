package org.letscareer.letscareer.domain.curation.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.curation.dto.request.CreateCurationRequestDto;
import org.letscareer.letscareer.domain.curation.dto.request.UpdateCurationRequestDto;
import org.letscareer.letscareer.domain.curation.dto.response.GetAdminCurationResponseDto;
import org.letscareer.letscareer.domain.curation.dto.response.GetAdminCurationsResponseDto;
import org.letscareer.letscareer.domain.curation.entity.Curation;
import org.letscareer.letscareer.domain.curation.helper.CurationHelper;
import org.letscareer.letscareer.domain.curation.mapper.CurationMapper;
import org.letscareer.letscareer.domain.curation.type.CurationLocationType;
import org.letscareer.letscareer.domain.curation.vo.AdminCurationDetailVo;
import org.letscareer.letscareer.domain.curation.vo.CurationAdminVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class CurationServiceImpl implements CurationService {
    private final CurationHelper curationHelper;
    private final CurationMapper curationMapper;

    @Override
    public GetAdminCurationsResponseDto getAdminCurations(CurationLocationType locationType) {
        List<CurationAdminVo> curationAdminVoList = curationHelper.findCurationAdminVosByLocationType(locationType);
        return curationMapper.toGetAdminCurationsResponseDto(curationAdminVoList);
    }

    @Override
    public GetAdminCurationResponseDto getAdminCuration(Long curationId) {
        AdminCurationDetailVo adminCurationDetailVo = curationHelper.findAdminCurationDetailVoByIdOrThrow(curationId);
        return curationMapper.toGetAdminCurationResponseDto(adminCurationDetailVo);
    }

    @Override
    public void createCuration(CurationLocationType locationType, CreateCurationRequestDto requestDto) {
        curationHelper.createCurationAndSave(locationType, requestDto);
    }

    @Override
    public void updateCuration(Long curationId, UpdateCurationRequestDto requestDto) {
        Curation curation = curationHelper.findCurationByIdOrThrow(curationId);
        curation.updateCuration(requestDto);
    }

    @Override
    public void deleteCuration(Long curationId) {
        Curation curation = curationHelper.findCurationByIdOrThrow(curationId);
        curationHelper.deleteCuration(curation);
    }
}
