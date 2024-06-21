package org.letscareer.letscareer.domain.contents.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.domain.contents.vo.ContentsDetailVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetContentsResponseDto(
        String title,
        String link,
        ContentsType contentsType
) {
    public static GetContentsResponseDto of(ContentsDetailVo vo) {
        return GetContentsResponseDto.builder()
                .title(vo.title())
                .link(vo.link())
                .contentsType(vo.contentsType())
                .build();
    }
}
