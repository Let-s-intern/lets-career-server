package org.letscareer.letscareer.domain.price.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.price.type.LivePriceType;
import org.letscareer.letscareer.global.common.utils.AbstractEnumCodeAttributeConverter;

@Converter
public class LivePriceConverter extends AbstractEnumCodeAttributeConverter<LivePriceType> {
    public LivePriceConverter() {
        super(LivePriceType.class);
    }
}
