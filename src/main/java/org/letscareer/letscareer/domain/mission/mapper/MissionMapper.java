package org.letscareer.letscareer.domain.mission.mapper;

import org.letscareer.letscareer.domain.application.vo.UserChallengeApplicationVo;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceDashboardVo;
import org.letscareer.letscareer.domain.challenge.dto.response.*;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeScheduleVo;
import org.letscareer.letscareer.domain.contents.vo.ContentsMissionVo;
import org.letscareer.letscareer.domain.mission.dto.request.CreateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.dto.response.MissionAdminListResponseDto;
import org.letscareer.letscareer.domain.mission.dto.response.MissionAdminResponseDto;
import org.letscareer.letscareer.domain.mission.dto.response.MissionApplicationScoreResponseDto;
import org.letscareer.letscareer.domain.mission.dto.response.MissionScoreResponseDto;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.mission.vo.DailyMissionVo;
import org.letscareer.letscareer.domain.mission.vo.MyDailyMissionVo;
import org.letscareer.letscareer.domain.mission.vo.MissionForChallengeVo;
import org.letscareer.letscareer.domain.missiontemplate.entity.MissionTemplate;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.attendance.vo.MissionScoreVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MissionMapper {
    public Mission toEntity(CreateMissionRequestDto createMissionRequestDto, Challenge challenge, MissionTemplate missionTemplate) {
        return Mission.createMission(createMissionRequestDto, challenge, missionTemplate);
    }

    public MissionScoreResponseDto toMissionScoreResponseDto(Integer th, Integer score) {
        return MissionScoreResponseDto.of(th, score);
    }

    public MissionAdminListResponseDto toMissionAdminListResponseDto(List<MissionAdminResponseDto> missionAdminResponseDtoList) {
        return MissionAdminListResponseDto.of(missionAdminResponseDtoList);
    }

    public MissionAdminResponseDto toMissionAdminResponseDto(MissionForChallengeVo vo,
                                                             Long applicationCount,
                                                             List<ContentsMissionVo> essentialContentsList,
                                                             List<ContentsMissionVo> additionalContentsList) {
        return MissionAdminResponseDto.of(vo, applicationCount, essentialContentsList, additionalContentsList);
    }

    public MissionApplicationScoreResponseDto toMissionApplicationScoreResponseDto(UserChallengeApplicationVo userChallengeApplicationVo,
                                                                                   List<MissionScoreResponseDto> scoreResponseDtoList,
                                                                                   Payment payment) {
        return MissionApplicationScoreResponseDto.of(userChallengeApplicationVo, scoreResponseDtoList, payment);
    }

    public GetChallengeDailyMissionResponseDto toGetChallengeDailyMissionResponseDto(DailyMissionVo dailyMissionVo) {
        return GetChallengeDailyMissionResponseDto.of(dailyMissionVo);
    }

    public GetChallengeMyDailyMissionResponseDto toGetChallengeMyDailyMissionResponseDto(MyDailyMissionVo myDailyMissionVo,
                                                                                         AttendanceDashboardVo attendanceInfo) {
        return GetChallengeMyDailyMissionResponseDto.of(myDailyMissionVo, attendanceInfo);
    }

    public GetChallengeScheduleResponseDto toGetChallengeScheduleResponseDto(List<ChallengeScheduleVo> challengeScheduleVoList) {
        return GetChallengeScheduleResponseDto.of(challengeScheduleVoList);
    }

    public GetChallengeMyMissionsResponseDto toGetChallengeMyMissionsResponseDto(List<?> missionVoList) {
        return GetChallengeMyMissionsResponseDto.of(missionVoList);
    }

    public GetChallengeMyMissionDetailResponseDto toGetChallengeMyMissionDetailResponseDto(MyDailyMissionVo missionInfo, AttendanceDashboardVo attendanceInfo) {
        return GetChallengeMyMissionDetailResponseDto.of(missionInfo, attendanceInfo);
    }
}
