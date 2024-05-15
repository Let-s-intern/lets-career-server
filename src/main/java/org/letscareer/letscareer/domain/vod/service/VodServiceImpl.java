package org.letscareer.letscareer.domain.vod.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.classification.dto.request.CreateVodClassificationRequestDto;
import org.letscareer.letscareer.domain.classification.helper.VodClassificationHelper;
import org.letscareer.letscareer.domain.classification.vo.VodClassificationDetailVo;
import org.letscareer.letscareer.domain.vod.dto.request.CreateVodRequestDto;
import org.letscareer.letscareer.domain.vod.dto.response.GetVodDetailResponseDto;
import org.letscareer.letscareer.domain.vod.entity.Vod;
import org.letscareer.letscareer.domain.vod.helper.VodHelper;
import org.letscareer.letscareer.domain.vod.mapper.VodMapper;
import org.letscareer.letscareer.domain.vod.vo.VodDetailVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class VodServiceImpl implements VodService {
    private final VodHelper vodHelper;
    private final VodMapper vodMapper;
    private final VodClassificationHelper vodClassificationHelper;

    @Override
    public GetVodDetailResponseDto getVodDetail(Long vodId) {
        VodDetailVo vodInfo = vodHelper.findVodDetailVoOrThrow(vodId);
        List<VodClassificationDetailVo> programTypeInfo = vodClassificationHelper.findVodClassificationVos(vodId);
        return vodMapper.createVodDetailResponseDto(vodInfo, programTypeInfo);
    }

    @Override
    public void createVod(CreateVodRequestDto createVodRequestDto) {
        Vod vod = vodHelper.createVodAndSave(createVodRequestDto);
        createClassificationListAndSave(createVodRequestDto.programTypeInfo(), vod);
    }

    @Override
    public void updateVod(Long vodId, CreateVodRequestDto createVodRequestDto) {
        Vod vod = vodHelper.findVodByIdOrThrow(vodId);
        vod.updateVod(createVodRequestDto);
        updateVodClassification(vod, createVodRequestDto.programTypeInfo());
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
