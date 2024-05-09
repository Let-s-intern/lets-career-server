package org.letscareer.letscareer.domain.contents.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.contents.vo.ContentsAdminVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record ContentsAdminListResponseDto(
        List<ContentsAdminVo> contentsAdminList,
        PageInfo pageInfo
) {
    public static ContentsAdminListResponseDto of(Page<ContentsAdminVo> contentsAdminList, PageInfo pageInfo) {
        return ContentsAdminListResponseDto.builder()
                .contentsAdminList(contentsAdminList.getContent())
                .pageInfo(pageInfo)
                .build();
    }
}
