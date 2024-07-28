package org.letscareer.letscareer.domain.user.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record UserApplicationResponseDto(
        UserInfoResponseDto userInfo,
        List<UserApplicationInfo> applicationInfo
) {
    public static UserApplicationResponseDto of(UserInfoResponseDto userInfo,
                                                List<UserApplicationInfo> applicationInfo) {
        return UserApplicationResponseDto.builder()
                .userInfo(userInfo)
                .applicationInfo(applicationInfo)
                .build();
    }
}
