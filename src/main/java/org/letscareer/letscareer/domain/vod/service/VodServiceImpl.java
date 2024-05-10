package org.letscareer.letscareer.domain.vod.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.classification.dto.request.CreateVodClassificationRequestDto;
import org.letscareer.letscareer.domain.classification.helper.VodClassificationHelper;
import org.letscareer.letscareer.domain.vod.dto.request.CreateVodRequestDto;
import org.letscareer.letscareer.domain.vod.entity.Vod;
import org.letscareer.letscareer.domain.vod.helper.VodHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class VodServiceImpl implements VodService {
    private final VodHelper vodHelper;
    private final VodClassificationHelper vodClassificationHelper;

    @Override
    public void createVod(CreateVodRequestDto createVodRequestDto) {
        Vod vod = vodHelper.createVodAndSave(createVodRequestDto);
        createClassificationListAndSave(createVodRequestDto.programTypeInfo(), vod);
    }

    @Override
    public void updateVod(Long vodId, CreateVodRequestDto createVodRequestDto) {

    }

    @Override
    public void deleteVod(Long vodId) {

    }

    public void createClassificationListAndSave(List<CreateVodClassificationRequestDto> requestDtoList,
                                                Vod vod) {
        requestDtoList.stream()
                .map(requestDto -> vodClassificationHelper.createVodClassificationAndSave(requestDto, vod))
                .collect(Collectors.toList());
    }
}
