package org.letscareer.letscareer.domain.curation.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.curation.type.CurationItemProgramType;
import org.letscareer.letscareer.global.common.utils.enm.AbstractEnumCodeAttributeConverter;

@Converter
public class CurationItemProgramTypeConverter extends AbstractEnumCodeAttributeConverter<CurationItemProgramType> {
    public CurationItemProgramTypeConverter() {
        super(CurationItemProgramType.class);
    }
}
