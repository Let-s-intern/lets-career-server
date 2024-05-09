package org.letscareer.letscareer.domain.mission.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.mission.type.MissionType;
import org.letscareer.letscareer.global.common.utils.AbstractEnumCodeAttributeConverter;

@Converter
public class MissionTypeConverter extends AbstractEnumCodeAttributeConverter<MissionType> {
    public MissionTypeConverter() {
        super(MissionType.class);
    }
}
