package org.letscareer.letscareer.domain.program.mapper;

import org.letscareer.letscareer.domain.program.dto.response.GetProgramForConditionResponseDto;
import org.letscareer.letscareer.domain.program.dto.response.GetProgramResponseDto;
import org.letscareer.letscareer.domain.program.dto.response.GetProgramsForConditionResponseDto;
import org.letscareer.letscareer.domain.program.dto.response.GetProgramsResponseDto;
import org.letscareer.letscareer.domain.program.vo.AdminProgramVo;
import org.letscareer.letscareer.domain.program.vo.ProgramForConditionVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProgramMapper {
    public GetProgramsResponseDto toGetProgramsResponseDto(List<GetProgramResponseDto<?>> contents, PageInfo pageInfo) {
        return GetProgramsResponseDto.of(contents, pageInfo);
    }

    public GetProgramResponseDto<?> toGetProgramResponseDto(AdminProgramVo content, List<?> classificationList) {
        return GetProgramResponseDto.of(content, classificationList);
    }

    public GetProgramsForConditionResponseDto toGetProgramsForConditionResponseDto(List<GetProgramForConditionResponseDto<?>> programList,
                                                                                   PageInfo pageInfo) {
        return GetProgramsForConditionResponseDto.of(programList, pageInfo);
    }

    public GetProgramForConditionResponseDto<?> toGetProgramForDurationResponseDto(ProgramForConditionVo programVo, List<?> classificationList) {
        return GetProgramForConditionResponseDto.of(programVo, classificationList);
    }
}
