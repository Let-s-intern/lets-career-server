package org.letscareer.letscareer.domain.curation.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.curation.type.CurationLocationType;
import org.letscareer.letscareer.global.common.utils.enm.AbstractEnumCodeAttributeConverter;

@Converter
public class CurationLocationTypeConverter extends AbstractEnumCodeAttributeConverter<CurationLocationType> {
    public CurationLocationTypeConverter() {
        super(CurationLocationType.class);
    }
}
