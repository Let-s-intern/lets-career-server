package org.letscareer.letscareer.domain.admincalssification.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.admincalssification.type.ProgramAdminClassification;
import org.letscareer.letscareer.global.common.utils.enm.AbstractEnumCodeAttributeConverter;

@Converter
public class ProgramAdminClassificationConverter extends AbstractEnumCodeAttributeConverter<ProgramAdminClassification> {
    public ProgramAdminClassificationConverter() {
        super(ProgramAdminClassification.class);
    }
}