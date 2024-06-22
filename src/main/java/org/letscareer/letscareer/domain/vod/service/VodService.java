package org.letscareer.letscareer.domain.vod.service;

import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.vod.dto.request.CreateVodRequestDto;
import org.letscareer.letscareer.domain.vod.dto.request.UpdateVodRequestDto;
import org.letscareer.letscareer.domain.vod.dto.response.GetVodDetailResponseDto;
import org.letscareer.letscareer.domain.vod.dto.response.GetVodsResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface VodService {
    GetVodsResponseDto getVodList(ProgramClassification type, Pageable pageable);
    GetVodDetailResponseDto getVodDetail(Long vodId);
    void createVod(CreateVodRequestDto createVodRequestDto);
    void updateVod(Long vodId, UpdateVodRequestDto updateVodRequestDto);
    void deleteVod(Long vodId);
}
