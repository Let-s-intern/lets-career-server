package org.letscareer.letscareer.domain.price.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.price.type.ChallengePriceType;
import org.letscareer.letscareer.global.common.utils.enm.AbstractEnumCodeAttributeConverter;

@Converter
public class ChallengePriceTypeConverter extends AbstractEnumCodeAttributeConverter<ChallengePriceType> {
    public ChallengePriceTypeConverter() {
        super(ChallengePriceType.class);
    }
}
