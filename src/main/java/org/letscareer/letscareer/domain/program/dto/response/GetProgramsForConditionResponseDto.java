package org.letscareer.letscareer.domain.program.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.global.common.entity.PageInfo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetProgramsForConditionResponseDto(
        List<GetProgramForConditionResponseDto<?>> programList,
        PageInfo pageInfo
) {
    public static GetProgramsForConditionResponseDto of(List<GetProgramForConditionResponseDto<?>> programList,
                                                        PageInfo pageInfo) {
        return GetProgramsForConditionResponseDto.builder()
                .programList(programList)
                .pageInfo(pageInfo)
                .build();
    }
}
