package org.letscareer.letscareer.domain.vod.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.vod.dto.request.CreateVodRequestDto;
import org.letscareer.letscareer.domain.vod.entity.Vod;
import org.letscareer.letscareer.domain.vod.repository.VodRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class VodHelper {
    private final VodRepository vodRepository;

    public Vod createVodAndSave(CreateVodRequestDto requestDto) {
        Vod vod = Vod.createVod(requestDto);
        return vodRepository.save(vod);
    }
}
