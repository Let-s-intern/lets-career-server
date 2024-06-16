package org.letscareer.letscareer.domain.mission.mapper;

import org.letscareer.letscareer.domain.application.vo.UserChallengeApplicationVo;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceDailyMissionVo;
import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengeDashboardDailyMissionResponseDto;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.mission.dto.request.CreateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.dto.response.MissionAdminListResponseDto;
import org.letscareer.letscareer.domain.mission.dto.response.MissionApplicationScoreResponseDto;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.mission.vo.DailyMissionVo;
import org.letscareer.letscareer.domain.mission.vo.MissionForChallengeVo;
import org.letscareer.letscareer.domain.missiontemplate.entity.MissionTemplate;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceScoreVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MissionMapper {
    public Mission toEntity(CreateMissionRequestDto createMissionRequestDto, Challenge challenge, MissionTemplate missionTemplate) {
        return Mission.createMission(createMissionRequestDto, challenge, missionTemplate);
    }

    public MissionAdminListResponseDto toMissionAdminListResponseDto(List<MissionForChallengeVo> missionForChallengeVos) {
        return MissionAdminListResponseDto.of(missionForChallengeVos);
    }

    public MissionApplicationScoreResponseDto toMissionApplicationScoreResponseDto(UserChallengeApplicationVo userChallengeApplicationVo,
                                                                                   List<AttendanceScoreVo> scores,
                                                                                   Payment payment) {
        return MissionApplicationScoreResponseDto.of(userChallengeApplicationVo, scores, payment);
    }

    public GetChallengeDashboardDailyMissionResponseDto toGetChallengeDashboardDailyMissionResponseDto(DailyMissionVo dailyMissionVo,
                                                                                                       AttendanceDailyMissionVo attendanceInfo) {
        return GetChallengeDashboardDailyMissionResponseDto.of(dailyMissionVo, attendanceInfo);
    }
}
