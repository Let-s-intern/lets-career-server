package org.letscareer.letscareer.domain.program.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.program.vo.ProgramForAdminVo;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record GetProgramWithCurrentCountResponseDto(
        Long id,
        ProgramType programType,
        ProgramStatusType programStatusType,
        String title,
        String thumbnail,
        Long currentCount,
        Integer participationCount,
        String zoomLink,
        String zoomPassword,
        Boolean isVisible,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime beginning,
        LocalDateTime deadline,
        LocalDateTime createdAt
) {
    public static GetProgramWithCurrentCountResponseDto of(ProgramForAdminVo vo, Long currentCount) {
        return GetProgramWithCurrentCountResponseDto.builder()
                .id(vo.id())
                .programType(vo.programType())
                .programStatusType(vo.programStatusType())
                .title(vo.title())
                .thumbnail(vo.thumbnail())
                .currentCount(currentCount)
                .participationCount(vo.participationCount())
                .zoomLink(vo.zoomLink())
                .zoomPassword(vo.zoomPassword())
                .isVisible(vo.isVisible())
                .startDate(vo.startDate())
                .endDate(vo.endDate())
                .beginning(vo.beginning())
                .deadline(vo.deadline())
                .createdAt(vo.createdAt())
                .build();
    }
}
