package org.letscareer.letscareer.domain.missiontemplate.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.missiontemplate.type.MissionTemplateTopic;
import org.letscareer.letscareer.global.common.utils.AbstractEnumCodeAttributeConverter;

@Converter
public class MissionTemplateTopicConverter extends AbstractEnumCodeAttributeConverter<MissionTemplateTopic> {
    public MissionTemplateTopicConverter() {
        super(MissionTemplateTopic.class);
    }
}
