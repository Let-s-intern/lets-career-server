package org.letscareer.letscareer.domain.vod.service;

import org.letscareer.letscareer.domain.vod.dto.request.CreateVodRequestDto;
import org.letscareer.letscareer.domain.vod.dto.response.GetVodDetailResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface VodService {
    GetVodDetailResponseDto getVodDetail(Long vodId);
    void createVod(CreateVodRequestDto createVodRequestDto);
    void updateVod(Long vodId, CreateVodRequestDto createVodRequestDto);
    void deleteVod(Long vodId);
}
