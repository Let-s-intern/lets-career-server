package org.letscareer.letscareer.domain.missiontemplate.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.missiontemplate.dto.request.UpdateMissionTemplateRequestDto;
import org.letscareer.letscareer.domain.missiontemplate.entity.MissionTemplate;
import org.letscareer.letscareer.domain.missiontemplate.repository.MissionTemplateRepository;
import org.letscareer.letscareer.domain.missiontemplate.vo.MissionTemplateAdminSimpleVo;
import org.letscareer.letscareer.domain.missiontemplate.vo.MissionTemplateAdminVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class MissionTemplateHelper {
    private final MissionTemplateRepository missionTemplateRepository;

    public MissionTemplate findMissionTemplateByIdOrThrow(Long missionTemplateId) {
        return missionTemplateRepository.findById(missionTemplateId).orElseThrow(EntityNotFoundException::new);
    }

    public void saveMissionTemplate(MissionTemplate missionTemplate) {
        missionTemplateRepository.save(missionTemplate);
    }

    public void updateMissionTemplate(Long missionTemplateId, UpdateMissionTemplateRequestDto updateMissionTemplateRequestDto) {
        MissionTemplate missionTemplate = findMissionTemplateByIdOrThrow(missionTemplateId);
        missionTemplate.updateMissionTemplate(updateMissionTemplateRequestDto);
    }

    public List<MissionTemplateAdminVo> findAllMissionTemplateAdminVos() {
        return missionTemplateRepository.findAllMissionTemplateAdminVos();
    }

    public List<MissionTemplateAdminSimpleVo> findAllMissionTemplateAdminSimpleVos() {
        return missionTemplateRepository.findAllMissionTemplateAdminSimpleVos();
    }
}
