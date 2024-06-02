package org.letscareer.letscareer.domain.live.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.live.vo.LiveApplicationFormVo;
import org.letscareer.letscareer.domain.price.vo.LivePriceDetailVo;
import org.letscareer.letscareer.domain.user.entity.User;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record GetLiveApplicationFormResponseDto(
        String name,
        String email,
        String contactEmail,
        String phoneNumber,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime deadline,
        LivePriceDetailVo price
) {
    public static GetLiveApplicationFormResponseDto of(User user,
                                                       LiveApplicationFormVo liveApplicationFormVo,
                                                       LivePriceDetailVo livePriceDetailVo) {
        return GetLiveApplicationFormResponseDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .contactEmail(user.getContactEmail())
                .phoneNumber(user.getPhoneNum())
                .startDate(liveApplicationFormVo.startDate())
                .endDate(liveApplicationFormVo.endDate())
                .deadline(liveApplicationFormVo.deadline())
                .price(livePriceDetailVo)
                .build();
    }
}
