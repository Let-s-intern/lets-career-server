package org.letscareer.letscareer.domain.contents.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.contents.vo.ContentsAdminSimpleVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record ContentsAdminSimpleListResponseDto(
        List<ContentsAdminSimpleVo> contentsSimpleList
) {
    public static ContentsAdminSimpleListResponseDto of(List<ContentsAdminSimpleVo> contentsSimpleList) {
        return ContentsAdminSimpleListResponseDto.builder()
                .contentsSimpleList(contentsSimpleList)
                .build();
    }
}
