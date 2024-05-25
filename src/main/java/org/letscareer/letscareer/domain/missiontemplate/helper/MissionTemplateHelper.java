package org.letscareer.letscareer.domain.missiontemplate.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.missiontemplate.entity.MissionTemplate;
import org.letscareer.letscareer.domain.missiontemplate.repository.MissionTemplateRepository;
import org.letscareer.letscareer.domain.missiontemplate.vo.MissionTemplateAdminSimpleVo;
import org.letscareer.letscareer.domain.missiontemplate.vo.MissionTemplateAdminVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.letscareer.letscareer.domain.missiontemplate.error.MissionTemplateErrorCode.MISSION_TEMPLATE_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class MissionTemplateHelper {
    private final MissionTemplateRepository missionTemplateRepository;

    public MissionTemplate findMissionTemplateByIdOrThrow(Long missionTemplateId) {
        return missionTemplateRepository.findById(missionTemplateId)
                .orElseThrow(() -> new EntityNotFoundException(MISSION_TEMPLATE_NOT_FOUND));
    }

    public void saveMissionTemplate(MissionTemplate missionTemplate) {
        missionTemplateRepository.save(missionTemplate);
    }

    public List<MissionTemplateAdminVo> findAllMissionTemplateAdminVos() {
        return missionTemplateRepository.findAllMissionTemplateAdminVos();
    }

    public List<MissionTemplateAdminSimpleVo> findAllMissionTemplateAdminSimpleVos() {
        return missionTemplateRepository.findAllMissionTemplateAdminSimpleVos();
    }

    public void deleteMissionTemplate(MissionTemplate missionTemplate) {
        missionTemplateRepository.delete(missionTemplate);
    }
}
