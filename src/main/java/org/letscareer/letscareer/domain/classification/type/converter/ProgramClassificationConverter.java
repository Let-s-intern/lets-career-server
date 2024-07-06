package org.letscareer.letscareer.domain.classification.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.global.common.utils.e.AbstractEnumCodeAttributeConverter;

@Converter
public class ProgramClassificationConverter extends AbstractEnumCodeAttributeConverter<ProgramClassification> {
    public ProgramClassificationConverter() {
        super(ProgramClassification.class);
    }
}