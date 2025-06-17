package org.letscareer.letscareer.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.helper.ChallengeApplicationHelper;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challenge.helper.ChallengeHelper;
import org.letscareer.letscareer.domain.challengeoption.entity.ChallengeOption;
import org.letscareer.letscareer.domain.challengeoption.helper.ChallengeOptionHelper;
import org.letscareer.letscareer.domain.contents.helper.ContentsHelper;
import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.domain.mission.dto.request.CreateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.dto.request.UpdateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.dto.response.FeedbackMissionAdminListResponseDto;
import org.letscareer.letscareer.domain.mission.dto.response.GetMissionDetailResponseDto;
import org.letscareer.letscareer.domain.mission.dto.response.MissionAdminListResponseDto;
import org.letscareer.letscareer.domain.mission.dto.response.MissionAdminResponseDto;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.mission.helper.MissionHelper;
import org.letscareer.letscareer.domain.mission.mapper.MissionMapper;
import org.letscareer.letscareer.domain.mission.vo.FeedbackMissionAdminVo;
import org.letscareer.letscareer.domain.mission.vo.MissionDetailVo;
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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class MissionServiceImpl implements MissionService {
    private final MissionHelper missionHelper;
    private final MissionMapper missionMapper;
    private final MissionContentsHelper missionContentsHelper;
    private final MissionTemplateHelper missionTemplateHelper;
    private final MissionScoreHelper missionScoreHelper;
    private final ChallengeApplicationHelper challengeApplicationHelper;
    private final ChallengeHelper challengeHelper;
    private final ChallengeOptionHelper challengeOptionHelper;
    private final ContentsHelper contentsHelper;

    @Override
    public GetMissionDetailResponseDto getMissionsDetail(Long missionId) {
        MissionDetailVo missionDetailVo = missionHelper.findMissionDetailVoOrThrow(missionId);
        return missionMapper.toGetMissionDetailResponseDto(missionDetailVo);
    }

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
        List<MissionAdminResponseDto> missionAdminResponseDtoList = createMissionAdminResponseDtoList(missionForChallengeVos, challengeId);
        return missionMapper.toMissionAdminListResponseDto(missionAdminResponseDtoList);
    }

    @Override
    public FeedbackMissionAdminListResponseDto getFeedbackMissionsForAdmin(Long challengeId) {
        List<FeedbackMissionAdminVo> feedbackMissionAdminVos = missionHelper.findFeedbackMissionAdminVodByChallengeId(challengeId);
        return missionMapper.toFeedbackMissionAdminListResponseDto(feedbackMissionAdminVos);
    }

    @Override
    public void updateMission(Long missionId, UpdateMissionRequestDto requestDto) {
        Mission mission = missionHelper.findMissionByIdOrThrow(missionId);
        mission.updateMission(requestDto);
        updateMissionTemplate(mission, requestDto.missionTemplateId());
        updateMissionContents(mission, ContentsType.ESSENTIAL, requestDto.essentialContentsIdList());
        updateMissionContents(mission, ContentsType.ADDITIONAL, requestDto.additionalContentsIdList());
        updateChallengeOption(mission, requestDto.challengeOptionId());
        MissionScore missionScore = mission.getMissionScore();
        missionScore.updateMissionScore(requestDto);
    }

    @Override
    public void deleteMission(Long missionId) {
        missionHelper.deleteMission(missionId);
    }

    private List<MissionAdminResponseDto> createMissionAdminResponseDtoList(List<MissionForChallengeVo> missionForChallengeVos, Long challengeId) {
        Long totalCount = challengeApplicationHelper.countChallengeApplications(challengeId);
        return missionForChallengeVos.stream()
                .map(missionForChallengeVo ->
                        missionMapper.toMissionAdminResponseDto(
                                missionForChallengeVo,
                                totalCount,
                                missionHelper.findContentsMissionVos(missionForChallengeVo.id(), ContentsType.ESSENTIAL),
                                missionHelper.findContentsMissionVos(missionForChallengeVo.id(), ContentsType.ADDITIONAL)
                        ))
                .collect(Collectors.toList());
    }

    private void updateMissionTemplate(Mission mission, Long missionTemplateId) {
        if(missionTemplateId == null) return;
        MissionTemplate missionTemplate = missionTemplateHelper.findMissionTemplateByIdOrThrow(missionTemplateId);
        mission.updateMissionTemplate(missionTemplate);
    }

    private void updateMissionContents(Mission mission, ContentsType contentsType, List<Long> contentsIdList) {
        if (contentsIdList == null || contentsIdList.isEmpty()) return;
        missionContentsHelper.deleteAllMissionContentsByMissionIdAndContentsType(mission.getId(), contentsType);
        findContentsAndCreateMissionContents(contentsType, contentsIdList, mission);
    }

    private void updateChallengeOption(Mission mission, Long challengeOptionId) {
        if(challengeOptionId == null) return;
        if(challengeOptionId == 0L) {
            mission.initChallengeOption();
            return;
        }
        ChallengeOption challengeOption = challengeOptionHelper.findChallengeOptionByChallengeOptionIdOrThrow(challengeOptionId);
        mission.updateChallengeOption(challengeOption);
    }

    private void findContentsAndCreateMissionContents(ContentsType contentsType, List<Long> contentsIdList, Mission mission) {
        if (contentsIdList == null || contentsIdList.isEmpty()) return;
        List<MissionContents> missionContentsList = contentsIdList.stream()
                .map(contentsId -> missionContentsHelper.createMissionContentsAndSave(mission, contentsHelper.findContentsByIdOrThrow(contentsId)))
                .toList();

        switch (contentsType) {
            case ESSENTIAL -> mission.setEssentialContentsList(missionContentsList);
            case ADDITIONAL -> mission.setAdditionalContentsList(missionContentsList);
        }
    }
}
