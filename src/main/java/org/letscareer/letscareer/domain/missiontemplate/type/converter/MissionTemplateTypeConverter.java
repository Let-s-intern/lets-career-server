package org.letscareer.letscareer.domain.missiontemplate.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.missiontemplate.type.MissionTemplateType;
import org.letscareer.letscareer.global.common.utils.AbstractEnumCodeAttributeConverter;

@Converter
public class MissionTemplateTypeConverter extends AbstractEnumCodeAttributeConverter<MissionTemplateType> {
    public MissionTemplateTypeConverter() {
        super(MissionTemplateType.class);
    }
}
