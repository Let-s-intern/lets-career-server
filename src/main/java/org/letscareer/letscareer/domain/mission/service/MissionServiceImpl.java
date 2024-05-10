package org.letscareer.letscareer.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.contents.entity.Contents;
import org.letscareer.letscareer.domain.contents.helper.ContentsHelper;
import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.domain.mission.dto.request.CreateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.mission.helper.MissionHelper;
import org.letscareer.letscareer.domain.mission.mapper.MissionMapper;
import org.letscareer.letscareer.domain.missiontemplate.entity.MissionTemplate;
import org.letscareer.letscareer.domain.missiontemplate.helper.MissionTemplateHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class MissionServiceImpl implements MissionService{
    private final MissionHelper missionHelper;
    private final MissionMapper missionMapper;
    private final MissionTemplateHelper missionTemplateHelper;
    private final ContentsHelper contentsHelper;

    @Override
    public void createMission(CreateMissionRequestDto createMissionRequestDto) {
        final MissionTemplate missionTemplate = missionTemplateHelper.findMissionTemplateByIdOrThrow(createMissionRequestDto.missionTemplateId());
        Mission newMission = missionMapper.toEntity(createMissionRequestDto, missionTemplate);
        findContentsAndAdd(ContentsType.ESSENTIAL, createMissionRequestDto.essentialContentsIdList(), newMission);
        findContentsAndAdd(ContentsType.ADDITIONAL, createMissionRequestDto.additionalContentsIdList(), newMission);
        findContentsAndAdd(ContentsType.LIMITED, createMissionRequestDto.limitedContentsIdList(), newMission);
        missionHelper.saveMission(newMission);
    }

    private void findContentsAndAdd(ContentsType contentsType, List<Long> contentsIdList, Mission newMission) {
        List<Contents> contentsList = contentsIdList.stream()
                .map(contentsHelper::findContentsByIdOrNull)
                .filter(Objects::nonNull)
                .toList();

        switch (contentsType) {
            case ESSENTIAL -> newMission.setEssentialContentsList(contentsList);
            case ADDITIONAL -> newMission.setAdditionalContents(contentsList);
            case LIMITED -> newMission.setLimitedContents(contentsList);
        }
    }
}
