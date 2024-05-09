package org.letscareer.letscareer.domain.missiontemplate.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.missiontemplate.entity.MissionTemplate;
import org.letscareer.letscareer.domain.missiontemplate.repository.MissionTemplateRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class MissionTemplateHelper {
    private final MissionTemplateRepository missionTemplateRepository;

    public void saveMissionTemplate(MissionTemplate missionTemplate) {
        missionTemplateRepository.save(missionTemplate);
    }
}
