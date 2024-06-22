package org.letscareer.letscareer.domain.mission.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.mission.type.MissionStatusType;
import org.letscareer.letscareer.global.common.utils.AbstractEnumCodeAttributeConverter;

@Converter
public class MissionStatusConverter extends AbstractEnumCodeAttributeConverter<MissionStatusType> {
    public MissionStatusConverter() {
        super(MissionStatusType.class);
    }
}
