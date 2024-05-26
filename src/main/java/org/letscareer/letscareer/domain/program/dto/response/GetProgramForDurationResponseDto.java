package org.letscareer.letscareer.domain.program.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.program.vo.ProgramForDurationVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetProgramForDurationResponseDto<T>(
        ProgramForDurationVo programInfo,
        T classificationList
) {
    public static <T> GetProgramForDurationResponseDto<?> of(ProgramForDurationVo programInfo,
                                                             T classificationList) {
        return GetProgramForDurationResponseDto.builder()
                .programInfo(programInfo)
                .classificationList(classificationList)
                .build();
    }
}
