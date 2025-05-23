package org.letscareer.letscareer.domain.application.mapper;

import org.letscareer.letscareer.domain.application.dto.response.GetChallengeApplicationsResponseDto;
import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationVo;
import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationWithOptionsVo;
import org.letscareer.letscareer.domain.application.vo.UserChallengeApplicationVo;
import org.letscareer.letscareer.domain.challenge.dto.response.*;
import org.letscareer.letscareer.domain.mission.dto.response.MissionApplicationScoreResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChallengeApplicationMapper {

    public GetChallengeApplicationsResponseDto toGetChallengeApplicationsResponseDto(List<AdminChallengeApplicationWithOptionsVo> vos) {
        return GetChallengeApplicationsResponseDto.of(vos);
    }

    public GetChallengeApplicationsPaybackResponseDto toGetChallengeApplicationsScoreResponseDto(List<MissionApplicationScoreResponseDto> missionApplications,
                                                                                                 Page<UserChallengeApplicationVo> challengeApplicationVos) {
        return GetChallengeApplicationsPaybackResponseDto.of(missionApplications, challengeApplicationVos);
    }

    public GetChallengeApplicationEmailListResponseDto toGetChallengeApplicationEmailListResponseDto(List<String> emailList) {
        return GetChallengeApplicationEmailListResponseDto.of(emailList);
    }

    public GetChallengeAccessResponseDto toGetChallengeAccessResponseDto(Boolean applied, Boolean isRefunded) {
        Boolean isAccessible = applied && !isRefunded;
        return GetChallengeAccessResponseDto.of(isAccessible);
    }

    public GetChallengeExisingApplicationResponseDto toChallengeExistingApplicationResponseDto(Boolean applied) {
        return GetChallengeExisingApplicationResponseDto.of(applied);
    }

    public GetChallengeGoalResponseDto toGetChallengeGoalResponseDto(String goal) {
        return GetChallengeGoalResponseDto.of(goal);
    }
}
