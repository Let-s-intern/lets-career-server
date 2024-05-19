package org.letscareer.letscareer.domain.missiontemplate.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.missiontemplate.vo.MissionTemplateAdminSimpleVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record MissionTemplateAdminSimpleListResponseDto(
        List<MissionTemplateAdminSimpleVo> missionTemplateAdminSimpleList
) {
    public static MissionTemplateAdminSimpleListResponseDto of(List<MissionTemplateAdminSimpleVo> missionTemplateAdminSimpleList) {
        return MissionTemplateAdminSimpleListResponseDto.builder()
                .missionTemplateAdminSimpleList(missionTemplateAdminSimpleList)
                .build();
    }
}
