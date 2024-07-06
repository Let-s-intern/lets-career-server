package org.letscareer.letscareer.domain.live.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.live.type.ProgressType;
import org.letscareer.letscareer.global.common.utils.e.AbstractEnumCodeAttributeConverter;

@Converter
public class ProgressTypeConverter extends AbstractEnumCodeAttributeConverter<ProgressType> {
    public ProgressTypeConverter() {
        super(ProgressType.class);
    }
}
