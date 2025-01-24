package org.letscareer.letscareer.domain.program.mapper;

import org.letscareer.letscareer.domain.challenge.vo.ChallengeRecommendVo;
import org.letscareer.letscareer.domain.live.vo.LiveRecommendVo;
import org.letscareer.letscareer.domain.program.dto.response.*;
import org.letscareer.letscareer.domain.program.vo.ProgramForAdminVo;
import org.letscareer.letscareer.domain.program.vo.ProgramForConditionVo;
import org.letscareer.letscareer.domain.report.vo.ReportRecommendVo;
import org.letscareer.letscareer.domain.vod.vo.VodDetailVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProgramMapper {

    public GetProgramsForAdminResponseDto toGetProgramsForAdminResponseDto(List<GetProgramForAdminResponseDto<?>> programList,
                                                                           PageInfo pageInfo) {
        return GetProgramsForAdminResponseDto.of(programList, pageInfo);
    }

    public GetProgramForAdminResponseDto<?> toGetProgramForAdminResponseDto(GetProgramWithCurrentCountResponseDto programVo, List<?> classificationList, List<?> adminClassificationList) {
        return GetProgramForAdminResponseDto.of(programVo, classificationList, adminClassificationList);
    }

    public GetProgramsForConditionResponseDto toGetProgramsForConditionResponseDto(List<GetProgramForConditionResponseDto<?>> programList,
                                                                                   PageInfo pageInfo) {
        return GetProgramsForConditionResponseDto.of(programList, pageInfo);
    }

    public GetProgramForConditionResponseDto<?> toGetProgramForDurationResponseDto(ProgramForConditionVo programVo, List<?> classificationList) {
        return GetProgramForConditionResponseDto.of(programVo, classificationList);
    }

    public GetProgramWithCurrentCountResponseDto toGetProgramWithCurrentCountResponseDto(ProgramForAdminVo vo, Long currentCount) {
        return GetProgramWithCurrentCountResponseDto.of(vo, currentCount);
    }

    public GetProgramsForRecommendResponseDto toGetProgramsForRecommendResponseDto(List<ChallengeRecommendVo> challengeList,
                                                                                   LiveRecommendVo live,
                                                                                   List<VodDetailVo> vodList,
                                                                                   List<ReportRecommendVo> reportList) {
        return GetProgramsForRecommendResponseDto.of(challengeList, live, vodList, reportList);
    }
}
