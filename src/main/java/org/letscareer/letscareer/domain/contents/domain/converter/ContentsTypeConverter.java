package org.letscareer.letscareer.domain.contents.domain.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.contents.domain.ContentsType;
import org.letscareer.letscareer.global.common.utils.AbstractEnumCodeAttributeConverter;

@Converter
public class ContentsTypeConverter extends AbstractEnumCodeAttributeConverter<ContentsType> {
    public ContentsTypeConverter() {
        super(ContentsType.class);
    }
}
