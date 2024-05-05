package org.letscareer.letscareer.domain.faq.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.faq.type.FaqProgramType;
import org.letscareer.letscareer.global.common.utils.AbstractEnumCodeAttributeConverter;

@Converter
public class FaqProgramTypeConverter extends AbstractEnumCodeAttributeConverter<FaqProgramType> {
    public FaqProgramTypeConverter() {
        super(FaqProgramType.class);
    }
}
