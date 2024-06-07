package org.letscareer.letscareer.domain.program.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.global.common.entity.PageInfo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetProgramsForAdminResponseDto(
        List<GetProgramForAdminResponseDto<?>> programList,
        PageInfo pageInfo
) {
    public static GetProgramsForAdminResponseDto of(List<GetProgramForAdminResponseDto<?>> programList,
                                                    PageInfo pageInfo) {
        return GetProgramsForAdminResponseDto.builder()
                .programList(programList)
                .pageInfo(pageInfo)
                .build();
    }
}
