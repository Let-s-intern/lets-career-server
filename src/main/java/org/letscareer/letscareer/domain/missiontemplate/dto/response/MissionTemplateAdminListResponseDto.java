package org.letscareer.letscareer.domain.missiontemplate.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.missiontemplate.vo.MissionTemplateAdminVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record MissionTemplateAdminListResponseDto(
        List<MissionTemplateAdminVo> missionTemplateAdminList
) {
    public static MissionTemplateAdminListResponseDto of(List<MissionTemplateAdminVo> missionTemplateAdminList) {
        return MissionTemplateAdminListResponseDto.builder()
                .missionTemplateAdminList(missionTemplateAdminList)
                .build();
    }
}
