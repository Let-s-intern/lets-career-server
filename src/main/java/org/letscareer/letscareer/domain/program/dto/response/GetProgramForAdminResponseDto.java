package org.letscareer.letscareer.domain.program.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.program.vo.ProgramForAdminVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetProgramForAdminResponseDto<T>(
        GetProgramWithCurrentCountResponseDto programInfo,
        T classificationList,
        T adminClassificationList
) {
    public static <T> GetProgramForAdminResponseDto<?> of(GetProgramWithCurrentCountResponseDto programInfo,
                                                          T classificationList,
                                                          T adminClassificationList) {
        return GetProgramForAdminResponseDto.builder()
                .programInfo(programInfo)
                .classificationList(classificationList)
                .adminClassificationList(adminClassificationList)
                .build();
    }
}
