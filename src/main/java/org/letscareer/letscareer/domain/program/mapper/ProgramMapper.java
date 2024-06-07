package org.letscareer.letscareer.domain.program.mapper;

import org.letscareer.letscareer.domain.program.dto.response.*;
import org.letscareer.letscareer.domain.program.vo.ProgramForAdminVo;
import org.letscareer.letscareer.domain.program.vo.ProgramForConditionVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProgramMapper {

    public GetProgramsForAdminResponseDto toGetProgramsForAdminResponseDto(List<GetProgramForAdminResponseDto<?>> programList,
                                                                           PageInfo pageInfo) {
        return GetProgramsForAdminResponseDto.of(programList, pageInfo);
    }

    public GetProgramForAdminResponseDto<?> toGetProgramForAdminResponseDto(ProgramForAdminVo programVo, List<?> classificationList) {
        return GetProgramForAdminResponseDto.of(programVo, classificationList);
    }

    public GetProgramsForConditionResponseDto toGetProgramsForConditionResponseDto(List<GetProgramForConditionResponseDto<?>> programList,
                                                                                   PageInfo pageInfo) {
        return GetProgramsForConditionResponseDto.of(programList, pageInfo);
    }

    public GetProgramForConditionResponseDto<?> toGetProgramForDurationResponseDto(ProgramForConditionVo programVo, List<?> classificationList) {
        return GetProgramForConditionResponseDto.of(programVo, classificationList);
    }
}
