package org.letscareer.letscareer.domain.price.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.price.type.LivePriceType;
import org.letscareer.letscareer.domain.price.type.converter.LivePriceConverter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@DiscriminatorValue("live_price")
@Getter
@Entity
public class LivePrice extends Live {
    @Convert(converter = LivePriceConverter.class)
    private LivePriceType livePriceType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "live_id")
    private Live live;
}
