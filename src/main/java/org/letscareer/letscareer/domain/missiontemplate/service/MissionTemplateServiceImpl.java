package org.letscareer.letscareer.domain.missiontemplate.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.missiontemplate.dto.request.CreateMissionTemplateRequestDto;
import org.letscareer.letscareer.domain.missiontemplate.dto.request.UpdateMissionTemplateRequestDto;
import org.letscareer.letscareer.domain.missiontemplate.entity.MissionTemplate;
import org.letscareer.letscareer.domain.missiontemplate.helper.MissionTemplateHelper;
import org.letscareer.letscareer.domain.missiontemplate.mapper.MissionTemplateMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MissionTemplateServiceImpl implements MissionTemplateService {
    private final MissionTemplateHelper missionTemplateHelper;
    private final MissionTemplateMapper missionTemplateMapper;

    @Override
    public void createMissionTemplate(CreateMissionTemplateRequestDto createMissionTemplateRequestDto) {
        MissionTemplate newMissionTemplate = missionTemplateMapper.toEntity(createMissionTemplateRequestDto);
        missionTemplateHelper.saveMissionTemplate(newMissionTemplate);
    }

    @Override
    public void updateMissionTemplate(Long missionTemplateId, UpdateMissionTemplateRequestDto updateMissionTemplateRequestDto) {
        missionTemplateHelper.updateMissionTemplate(missionTemplateId, updateMissionTemplateRequestDto);
    }
}
