package org.letscareer.letscareer.domain.file.vo;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record FileVo(
        String fileName,
        String url
) {
    public static FileVo of(String originalFileName, String url) {
        return FileVo.builder()
                .fileName(originalFileName)
                .url(url)
                .build();
    }
}
