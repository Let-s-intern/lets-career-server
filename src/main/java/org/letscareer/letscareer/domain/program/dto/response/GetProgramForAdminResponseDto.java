package org.letscareer.letscareer.domain.program.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.program.vo.ProgramForAdminVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetProgramForAdminResponseDto<T>(
        ProgramForAdminVo programInfo,
        T classificationList
) {
    public static <T> GetProgramForAdminResponseDto<?> of(ProgramForAdminVo programInfo,
                                                          T classificationList) {
        return GetProgramForAdminResponseDto.builder()
                .programInfo(programInfo)
                .classificationList(classificationList)
                .build();
    }
}
