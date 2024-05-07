package org.letscareer.letscareer.domain.price.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.live.dto.request.CreateLiveRequestDto;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.price.dto.request.CreateLivePriceRequestDto;
import org.letscareer.letscareer.domain.price.type.LivePriceType;
import org.letscareer.letscareer.domain.price.type.converter.LivePriceConverter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("live_price")
@Getter
@Entity
public class LivePrice extends Price {
    @Convert(converter = LivePriceConverter.class)
    private LivePriceType livePriceType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "live_id")
    private Live live;

    @Builder(access = AccessLevel.PRIVATE)
    public LivePrice(CreateLivePriceRequestDto requestDto,
                     Live live) {
        super(requestDto.priceInfo());
        this.livePriceType = requestDto.livePriceType();
        this.live = live;
    }

    public static LivePrice createLivePrice(CreateLivePriceRequestDto requestDto,
                                            Live live) {
        return LivePrice.builder()
                .requestDto(requestDto)
                .live(live)
                .build();
    }
}
