package org.letscareer.letscareer.domain.vod.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.classification.dto.request.CreateVodClassificationRequestDto;
import org.letscareer.letscareer.domain.classification.helper.VodClassificationHelper;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.classification.vo.VodClassificationDetailVo;
import org.letscareer.letscareer.domain.vod.dto.request.CreateVodRequestDto;
import org.letscareer.letscareer.domain.vod.dto.request.UpdateVodRequestDto;
import org.letscareer.letscareer.domain.vod.dto.response.GetVodDetailResponseDto;
import org.letscareer.letscareer.domain.vod.dto.response.GetVodsResponseDto;
import org.letscareer.letscareer.domain.vod.entity.Vod;
import org.letscareer.letscareer.domain.vod.helper.VodHelper;
import org.letscareer.letscareer.domain.vod.mapper.VodMapper;
import org.letscareer.letscareer.domain.vod.vo.VodDetailVo;
import org.letscareer.letscareer.domain.vod.vo.VodProfileVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class VodServiceImpl implements VodService {
    private final VodHelper vodHelper;
    private final VodMapper vodMapper;
    private final VodClassificationHelper vodClassificationHelper;

    @Override
    public GetVodsResponseDto getVodList(ProgramClassification type, Pageable pageable) {
        Page<VodProfileVo> vodProfileVos = vodHelper.findVodProfileVos(type, pageable);
        return vodMapper.toGetVodsResponseDto(vodProfileVos);
    }

    @Override
    public GetVodDetailResponseDto getVodDetail(Long vodId) {
        VodDetailVo vodInfo = vodHelper.findVodDetailVoOrThrow(vodId);
        List<VodClassificationDetailVo> programTypeInfo = vodClassificationHelper.findVodClassificationVos(vodId);
        return vodMapper.toVodDetailResponseDto(vodInfo, programTypeInfo);
    }

    @Override
    public void createVod(CreateVodRequestDto createVodRequestDto) {
        Vod vod = vodHelper.createVodAndSave(createVodRequestDto);
        createClassificationListAndSave(createVodRequestDto.programTypeInfo(), vod);
    }

    @Override
    public void updateVod(Long vodId, UpdateVodRequestDto updateVodRequestDto) {
        Vod vod = vodHelper.findVodByIdOrThrow(vodId);
        vod.updateVod(updateVodRequestDto);
        updateVodClassification(vod, updateVodRequestDto.programTypeInfo());
    }

    @Override
    public void deleteVod(Long vodId) {
        vodHelper.deleteVodById(vodId);
    }

    public void createClassificationListAndSave(List<CreateVodClassificationRequestDto> requestDtoList,
                                                Vod vod) {
        requestDtoList.stream()
                .map(requestDto -> vodClassificationHelper.createVodClassificationAndSave(requestDto, vod))
                .collect(Collectors.toList());
    }

    private void updateVodClassification(Vod vod, List<CreateVodClassificationRequestDto> programTypeInfo) {
        if (Objects.isNull(programTypeInfo)) return;
        vodClassificationHelper.deleteVodClassificationsByVodId(vod.getId());
        vod.setInitClassifications();
        createClassificationListAndSave(programTypeInfo, vod);
    }
}
