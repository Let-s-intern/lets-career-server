package org.letscareer.letscareer.domain.program.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.program.vo.AdminProgramVo;

@Builder(access = AccessLevel.PACKAGE)
public record GetProgramResponseDto<T>(
        AdminProgramVo programInfo,
        T classificationList
) {
    public static <T> GetProgramResponseDto<?> of(AdminProgramVo programInfo,
                                               T classificationList) {
        return GetProgramResponseDto.builder()
                .programInfo(programInfo)
                .classificationList(classificationList)
                .build();
    }
}
