package org.letscareer.letscareer.domain.curation.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.curation.dto.request.CreateCurationItemRequestDto;
import org.letscareer.letscareer.domain.curation.dto.request.CreateCurationRequestDto;
import org.letscareer.letscareer.domain.curation.dto.request.UpdateCurationRequestDto;
import org.letscareer.letscareer.domain.curation.dto.response.GetAdminCurationResponseDto;
import org.letscareer.letscareer.domain.curation.dto.response.GetAdminCurationsResponseDto;
import org.letscareer.letscareer.domain.curation.dto.response.GetCurationResponseDto;
import org.letscareer.letscareer.domain.curation.entity.Curation;
import org.letscareer.letscareer.domain.curation.helper.CurationHelper;
import org.letscareer.letscareer.domain.curation.helper.CurationItemHelper;
import org.letscareer.letscareer.domain.curation.mapper.CurationMapper;
import org.letscareer.letscareer.domain.curation.type.CurationLocationType;
import org.letscareer.letscareer.domain.curation.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class CurationServiceImpl implements CurationService {
    private final CurationHelper curationHelper;
    private final CurationItemHelper curationItemHelper;
    private final CurationMapper curationMapper;

    @Override
    public GetAdminCurationsResponseDto getAdminCurations(CurationLocationType locationType) {
        List<AdminCurationVo> adminCurationVoList = curationHelper.findAdminCurationVosByLocationType(locationType);
        return curationMapper.toGetAdminCurationsResponseDto(adminCurationVoList);
    }

    @Override
    public GetAdminCurationResponseDto getAdminCuration(Long curationId) {
        AdminCurationDetailVo adminCurationDetailVo = curationHelper.findAdminCurationDetailVoByIdOrThrow(curationId);
        List<AdminCurationItemVo> adminCurationItemVos = curationItemHelper.findAllAdminCurationItemVosByCurationId(curationId);
        return curationMapper.toGetAdminCurationResponseDto(adminCurationDetailVo, adminCurationItemVos);
    }

    @Override
    public GetCurationResponseDto getCuration(CurationLocationType locationType) {
        CurationVo curationVo = curationHelper.findCurationVoByLocationType(locationType);
        List<CurationItemVo> curationItemVos = curationVo != null ? curationItemHelper.findAllCurationItemVosByCurationId(curationVo.curationId()) : null;
        return curationMapper.toGetCurationResponseDto(curationVo, curationItemVos);
    }

    @Override
    public void createCuration(CurationLocationType locationType, CreateCurationRequestDto requestDto) {
        Curation curation = curationHelper.createCurationAndSave(locationType, requestDto);
        createCurationItemListAndSave(curation, requestDto.curationItemList());
    }

    @Override
    public void updateCuration(Long curationId, UpdateCurationRequestDto requestDto) {
        Curation curation = curationHelper.findCurationByIdOrThrow(curationId);
        curation.updateCuration(requestDto);
        updateCurationItemList(curation, requestDto.curationItemList());
    }

    @Override
    public void deleteCuration(Long curationId) {
        Curation curation = curationHelper.findCurationByIdOrThrow(curationId);
        curationHelper.deleteCuration(curation);
    }

    private void createCurationItemListAndSave(Curation curation, List<CreateCurationItemRequestDto> curationItemList) {
        curationItemList.stream()
                .map(curationItem -> curationItemHelper.createCurationItemAndSave(curation, curationItem))
                .collect(Collectors.toList());
    }

    private void updateCurationItemList(Curation curation, List<CreateCurationItemRequestDto> curationItemList) {
        if(Objects.isNull(curationItemList)) return;
        curationItemHelper.deleteAllCurationItemsByCurationId(curation.getId());
        curation.setInitCurationItemList();
        createCurationItemListAndSave(curation, curationItemList);
    }
}
