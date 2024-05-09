package org.letscareer.letscareer.domain.price.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.price.dto.request.CreateLivePriceRequestDto;
import org.letscareer.letscareer.domain.price.entity.LivePrice;
import org.letscareer.letscareer.domain.price.repository.LivePriceRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class LivePriceHelper {
    private final LivePriceRepository livePriceRepository;

    public LivePrice createLivePriceAndSave(CreateLivePriceRequestDto requestDto,
                                            Live live) {
        LivePrice livePrice = LivePrice.createLivePrice(requestDto, live);
        return livePriceRepository.save(livePrice);
    }
}
