package org.letscareer.letscareer.domain.vod.service;

import org.letscareer.letscareer.domain.vod.dto.request.CreateVodRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface VodService {
    void createVod(CreateVodRequestDto createVodRequestDto);
}
