package org.letscareer.letscareer.domain.program.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.program.vo.AdminProgramVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetProgramsResponseDto(
        List<GetProgramResponseDto<?>> programList,
        PageInfo pageInfo
) {
    public static GetProgramsResponseDto of(List<GetProgramResponseDto<?>> contents, PageInfo pageInfo) {
        return GetProgramsResponseDto.builder()
                .programList(contents)
                .pageInfo(pageInfo)
                .build();
    }
}
