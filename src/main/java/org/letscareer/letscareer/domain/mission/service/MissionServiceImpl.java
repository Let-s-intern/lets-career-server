package org.letscareer.letscareer.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challenge.helper.ChallengeHelper;
import org.letscareer.letscareer.domain.contents.helper.ContentsHelper;
import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.domain.mission.dto.request.CreateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.dto.request.UpdateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.dto.response.MissionAdminListResponseDto;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.mission.helper.MissionHelper;
import org.letscareer.letscareer.domain.mission.mapper.MissionMapper;
import org.letscareer.letscareer.domain.mission.vo.MissionForChallengeVo;
import org.letscareer.letscareer.domain.missioncontents.entity.MissionContents;
import org.letscareer.letscareer.domain.missioncontents.helper.MissionContentsHelper;
import org.letscareer.letscareer.domain.missiontemplate.entity.MissionTemplate;
import org.letscareer.letscareer.domain.missiontemplate.helper.MissionTemplateHelper;
import org.letscareer.letscareer.domain.score.entity.MissionScore;
import org.letscareer.letscareer.domain.score.helper.MissionScoreHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Transactional
@Service
public class MissionServiceImpl implements MissionService {
    private final MissionHelper missionHelper;
    private final MissionMapper missionMapper;
    private final MissionContentsHelper missionContentsHelper;
    private final MissionScoreHelper missionScoreHelper;
    private final MissionTemplateHelper missionTemplateHelper;
    private final ChallengeHelper challengeHelper;
    private final ContentsHelper contentsHelper;

    @Override
    public void createMission(Long challengeId, CreateMissionRequestDto createMissionRequestDto) {
        Challenge challenge = challengeHelper.findChallengeByIdOrThrow(challengeId);
        MissionTemplate missionTemplate = missionTemplateHelper.findMissionTemplateByIdOrThrow(createMissionRequestDto.missionTemplateId());
        Mission newMission = missionMapper.toEntity(createMissionRequestDto, challenge, missionTemplate);
        missionHelper.saveMission(newMission);
        findContentsAndCreateMissionContents(ContentsType.ESSENTIAL, createMissionRequestDto.essentialContentsIdList(), newMission);
        findContentsAndCreateMissionContents(ContentsType.ADDITIONAL, createMissionRequestDto.additionalContentsIdList(), newMission);
        missionScoreHelper.createMissionScore(createMissionRequestDto, newMission);
    }

    @Override
    public MissionAdminListResponseDto getMissionsForAdmin(Long challengeId) {
        List<MissionForChallengeVo> missionForChallengeVos = missionHelper.findMissionForChallengeVos(challengeId);
        return missionMapper.toMissionAdminListResponseDto(missionForChallengeVos);
    }

    @Override
    public void updateMission(Long missionId, UpdateMissionRequestDto updateMissionRequestDto) {
        Mission mission = missionHelper.findMissionByIdOrThrow(missionId);
        mission.updateMission(updateMissionRequestDto);
        updateMissionContents(mission, ContentsType.ESSENTIAL, updateMissionRequestDto.essentialContentsIdList());
        updateMissionContents(mission, ContentsType.ADDITIONAL, updateMissionRequestDto.additionalContentsIdList());
        MissionScore missionScore = mission.getMissionScore();
        missionScore.updateMissionScore(updateMissionRequestDto);
    }

    private void updateMissionContents(Mission mission, ContentsType contentsType, List<Long> contentsIdList) {
        if(contentsIdList == null || contentsIdList.isEmpty()) return;
        missionContentsHelper.deleteAllMissionContentsByMissionIdAndContentsType(mission.getId(), contentsType);
        mission.setInitMissionContentsList(contentsType);
        findContentsAndCreateMissionContents(contentsType, contentsIdList, mission);
    }

    private void findContentsAndCreateMissionContents(ContentsType contentsType, List<Long> contentsIdList, Mission mission) {
        if(contentsIdList == null || contentsIdList.isEmpty()) return;
        List<MissionContents> missionContentsList = contentsIdList.stream()
                .map(contentsId -> missionContentsHelper.createMissionContentsAndSave(mission, contentsHelper.findContentsByIdOrThrow(contentsId)))
                .toList();

        switch (contentsType) {
            case ESSENTIAL -> mission.setEssentialContentsList(missionContentsList);
            case ADDITIONAL -> mission.setAdditionalContentsList(missionContentsList);
        }
    }
}
