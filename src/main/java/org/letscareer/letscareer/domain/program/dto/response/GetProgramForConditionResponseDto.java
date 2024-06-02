package org.letscareer.letscareer.domain.program.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.program.vo.ProgramForConditionVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetProgramForConditionResponseDto<T>(
        ProgramForConditionVo programInfo,
        T classificationList
) {
    public static <T> GetProgramForConditionResponseDto<?> of(ProgramForConditionVo programInfo,
                                                              T classificationList) {
        return GetProgramForConditionResponseDto.builder()
                .programInfo(programInfo)
                .classificationList(classificationList)
                .build();
    }
}
