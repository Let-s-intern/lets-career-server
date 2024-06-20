package org.letscareer.letscareer.domain.live.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.live.vo.LiveApplicationFormVo;
import org.letscareer.letscareer.domain.price.vo.LivePriceDetailVo;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.user.entity.User;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder(access = AccessLevel.PRIVATE)
public record GetLiveApplicationFormResponseDto(
        Boolean applied,
        String name,
        String email,
        String contactEmail,
        String phoneNumber,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime deadline,
        ProgramStatusType statusType,
        LivePriceDetailVo price
) {
    public static GetLiveApplicationFormResponseDto of(User user,
                                                       Boolean applied,
                                                       LiveApplicationFormVo liveApplicationFormVo,
                                                       LivePriceDetailVo livePriceDetailVo) {
        return GetLiveApplicationFormResponseDto.builder()
                .applied(applied)
                .name(Objects.isNull(user) ? null : user.getName())
                .email(Objects.isNull(user) ? null : user.getEmail())
                .contactEmail(Objects.isNull(user) ? null : user.getContactEmail())
                .phoneNumber(Objects.isNull(user) ? null : user.getPhoneNum())
                .startDate(liveApplicationFormVo.startDate())
                .endDate(liveApplicationFormVo.endDate())
                .deadline(liveApplicationFormVo.deadline())
                .statusType(liveApplicationFormVo.statusType())
                .price(livePriceDetailVo)
                .build();
    }
}
