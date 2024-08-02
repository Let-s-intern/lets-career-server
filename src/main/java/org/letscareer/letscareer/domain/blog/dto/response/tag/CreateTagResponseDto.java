package org.letscareer.letscareer.domain.blog.dto.response.tag;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.blog.entity.HashTag;

@Builder(access = AccessLevel.PRIVATE)
public record CreateTagResponseDto(
        Long id,
        String title
) {
    public static CreateTagResponseDto of(HashTag hashTag) {
        return CreateTagResponseDto.builder()
                .id(hashTag.getId())
                .title(hashTag.getTitle())
                .build();
    }
}
