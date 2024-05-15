package org.letscareer.letscareer.domain.price.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.price.dto.request.CreateLivePriceRequestDto;
import org.letscareer.letscareer.domain.price.entity.LivePrice;
import org.letscareer.letscareer.domain.price.error.PriceErrorCode;
import org.letscareer.letscareer.domain.price.repository.LivePriceRepository;
import org.letscareer.letscareer.domain.price.vo.LivePriceDetailVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LivePriceHelper {
    private final LivePriceRepository livePriceRepository;

    public LivePrice createLivePriceAndSave(CreateLivePriceRequestDto requestDto,
                                            Live live) {
        LivePrice livePrice = LivePrice.createLivePrice(requestDto, live);
        return livePriceRepository.save(livePrice);
    }

    public LivePriceDetailVo findLivePriceDetailVos(Long liveId) {
        return livePriceRepository.findLivePriceDetailVo(liveId)
                .orElseThrow(() -> new EntityNotFoundException(PriceErrorCode.LIVE_PRICE_NOT_FOUND));
    }

    public void deleteLivePriceByLiveId(Long liveId) {
        livePriceRepository.deleteAllByLiveId(liveId);
    }
}
