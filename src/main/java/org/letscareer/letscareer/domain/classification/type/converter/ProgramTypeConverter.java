package org.letscareer.letscareer.domain.classification.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.classification.type.ProgramType;
import org.letscareer.letscareer.global.common.utils.AbstractEnumCodeAttributeConverter;

@Converter
public class ProgramTypeConverter extends AbstractEnumCodeAttributeConverter<ProgramType> {
    public ProgramTypeConverter() {
        super(ProgramType.class);
    }
}