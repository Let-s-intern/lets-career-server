package org.letscareer.letscareer.domain.file.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record CreateFileResponseDto(
        String fileUrl
) {
    public static CreateFileResponseDto of(String fileUrl) {
        return CreateFileResponseDto.builder()
                .fileUrl(fileUrl)
                .build();
    }
}
