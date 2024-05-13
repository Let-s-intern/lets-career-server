package org.letscareer.letscareer.domain.missiontemplate.repository;

import org.letscareer.letscareer.domain.missiontemplate.vo.MissionTemplateAdminVo;

import java.util.List;

public interface MissionTemplateQueryRepository {
    List<MissionTemplateAdminVo> findAllMissionTemplateAdminVos();
}
