package org.letscareer.letscareer.domain.missiontemplate.mapper;

import org.letscareer.letscareer.domain.missiontemplate.dto.request.CreateMissionTemplateRequestDto;
import org.letscareer.letscareer.domain.missiontemplate.entity.MissionTemplate;
import org.springframework.stereotype.Component;

@Component
public class MissionTemplateMapper {
    public MissionTemplate toEntity(CreateMissionTemplateRequestDto createMissionTemplateRequestDto) {
        return MissionTemplate.createMissionTemplate(createMissionTemplateRequestDto);
    }
}
