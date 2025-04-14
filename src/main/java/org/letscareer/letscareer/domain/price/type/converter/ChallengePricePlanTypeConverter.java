package org.letscareer.letscareer.domain.price.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.price.type.ChallengePricePlanType;
import org.letscareer.letscareer.global.common.utils.enm.AbstractEnumCodeAttributeConverter;

@Converter
public class ChallengePricePlanTypeConverter extends AbstractEnumCodeAttributeConverter<ChallengePricePlanType> {
    public ChallengePricePlanTypeConverter() {
        super(ChallengePricePlanType.class);
    }
}
