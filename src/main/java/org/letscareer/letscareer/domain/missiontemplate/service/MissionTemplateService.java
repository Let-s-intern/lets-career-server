package org.letscareer.letscareer.domain.missiontemplate.service;

import org.letscareer.letscareer.domain.missiontemplate.dto.request.CreateMissionTemplateRequestDto;
import org.letscareer.letscareer.domain.missiontemplate.dto.request.UpdateMissionTemplateRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface MissionTemplateService {
    void createMissionTemplate(CreateMissionTemplateRequestDto createMissionTemplateRequestDto);

    void updateMissionTemplate(Long missionTemplateId, UpdateMissionTemplateRequestDto updateMissionTemplateRequestDto);
}
