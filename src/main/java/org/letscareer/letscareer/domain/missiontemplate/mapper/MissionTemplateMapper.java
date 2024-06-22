package org.letscareer.letscareer.domain.missiontemplate.mapper;

import org.letscareer.letscareer.domain.missiontemplate.dto.request.CreateMissionTemplateRequestDto;
import org.letscareer.letscareer.domain.missiontemplate.dto.response.MissionTemplateAdminListResponseDto;
import org.letscareer.letscareer.domain.missiontemplate.dto.response.MissionTemplateAdminSimpleListResponseDto;
import org.letscareer.letscareer.domain.missiontemplate.entity.MissionTemplate;
import org.letscareer.letscareer.domain.missiontemplate.vo.MissionTemplateAdminSimpleVo;
import org.letscareer.letscareer.domain.missiontemplate.vo.MissionTemplateAdminVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MissionTemplateMapper {
    public MissionTemplate toEntity(CreateMissionTemplateRequestDto createMissionTemplateRequestDto) {
        return MissionTemplate.createMissionTemplate(createMissionTemplateRequestDto);
    }

    public MissionTemplateAdminListResponseDto toMissionTemplateAdminListResponseDto(List<MissionTemplateAdminVo> missionTemplateAdminList) {
        return MissionTemplateAdminListResponseDto.of(missionTemplateAdminList);
    }

    public MissionTemplateAdminSimpleListResponseDto toMissionTemplateAdminSimpleListResponseDto(List<MissionTemplateAdminSimpleVo> missionTemplateAdminSimpleList) {
        return MissionTemplateAdminSimpleListResponseDto.of(missionTemplateAdminSimpleList);
    }
}
