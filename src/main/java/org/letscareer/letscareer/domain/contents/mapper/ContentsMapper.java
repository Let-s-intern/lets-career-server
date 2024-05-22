package org.letscareer.letscareer.domain.contents.mapper;

import org.letscareer.letscareer.domain.contents.dto.request.CreateContentsRequestDto;
import org.letscareer.letscareer.domain.contents.dto.response.ContentsAdminListResponseDto;
import org.letscareer.letscareer.domain.contents.dto.response.ContentsAdminSimpleListResponseDto;
import org.letscareer.letscareer.domain.contents.entity.Contents;
import org.letscareer.letscareer.domain.contents.vo.ContentsAdminSimpleVo;
import org.letscareer.letscareer.domain.contents.vo.ContentsAdminVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContentsMapper {
    public Contents toEntity(CreateContentsRequestDto createContentsRequestDto) {
        return Contents.createContents(createContentsRequestDto);
    }

    public ContentsAdminListResponseDto toContentsAdminListResponseDto(Page<ContentsAdminVo> contentsAdminList) {
        PageInfo pageInfo = PageInfo.of(contentsAdminList);
        return ContentsAdminListResponseDto.of(contentsAdminList, pageInfo);
    }

    public ContentsAdminSimpleListResponseDto toContentsAdminSimpleListResponseDto(List<ContentsAdminSimpleVo> contentsAdminSimpleList) {
        return ContentsAdminSimpleListResponseDto.of(contentsAdminSimpleList);
    }
}
