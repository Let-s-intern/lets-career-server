package org.letscareer.letscareer.domain.live.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetLiveExisingApplicationResponseDto(
        Boolean applied
) {
    public static GetLiveExisingApplicationResponseDto of(Boolean applied) {
        return GetLiveExisingApplicationResponseDto.builder()
                .applied(applied)
                .build();
    }
}
