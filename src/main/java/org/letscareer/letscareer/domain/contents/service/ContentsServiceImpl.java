package org.letscareer.letscareer.domain.contents.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.contents.dto.request.CreateContentsRequestDto;
import org.letscareer.letscareer.domain.contents.dto.request.UpdateContentsRequestDto;
import org.letscareer.letscareer.domain.contents.dto.response.ContentsAdminListResponseDto;
import org.letscareer.letscareer.domain.contents.entity.Contents;
import org.letscareer.letscareer.domain.contents.helper.ContentsHelper;
import org.letscareer.letscareer.domain.contents.mapper.ContentsMapper;
import org.letscareer.letscareer.domain.contents.vo.ContentsAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ContentsServiceImpl implements ContentsService {
    private final ContentsHelper contentsHelper;
    private final ContentsMapper contentsMapper;

    @Override
    public void createContents(CreateContentsRequestDto createContentsRequestDto) {
        Contents newContents = contentsMapper.toEntity(createContentsRequestDto);
        contentsHelper.saveContents(newContents);
    }

    @Override
    public ContentsAdminListResponseDto getAllContents(Pageable pageable) {
        Page<ContentsAdminVo> contentsAdminList = contentsHelper.findAllContentsAdminVos(pageable);
        return contentsMapper.toContentsAdminListResponseDto(contentsAdminList);
    }

    @Override
    public void updateContents(Long contentsId, UpdateContentsRequestDto updateContentsRequestDto) {
        contentsHelper.updateContents(contentsId, updateContentsRequestDto);
    }
}
