package org.letscareer.letscareer.domain.contents.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.global.common.utils.e.AbstractEnumCodeAttributeConverter;

@Converter
public class ContentsTypeConverter extends AbstractEnumCodeAttributeConverter<ContentsType> {
    public ContentsTypeConverter() {
        super(ContentsType.class);
    }
}
