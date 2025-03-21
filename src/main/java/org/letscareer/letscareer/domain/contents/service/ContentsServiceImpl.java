package org.letscareer.letscareer.domain.contents.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.contents.dto.request.CreateContentsRequestDto;
import org.letscareer.letscareer.domain.contents.dto.request.GetContentsResponseDto;
import org.letscareer.letscareer.domain.contents.dto.request.UpdateContentsRequestDto;
import org.letscareer.letscareer.domain.contents.dto.response.ContentsAdminListResponseDto;
import org.letscareer.letscareer.domain.contents.dto.response.ContentsAdminSimpleListResponseDto;
import org.letscareer.letscareer.domain.contents.entity.Contents;
import org.letscareer.letscareer.domain.contents.helper.ContentsHelper;
import org.letscareer.letscareer.domain.contents.mapper.ContentsMapper;
import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.domain.contents.vo.ContentsAdminSimpleVo;
import org.letscareer.letscareer.domain.contents.vo.ContentsAdminVo;
import org.letscareer.letscareer.domain.contents.vo.ContentsDetailVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ContentsServiceImpl implements ContentsService {
    private final ContentsHelper contentsHelper;
    private final ContentsMapper contentsMapper;

    @Override
    public GetContentsResponseDto getContentsDetail(Long contentsId) {
        ContentsDetailVo contentsDetailVo = contentsHelper.findContentsDetailVoOrThrow(contentsId);
        return contentsMapper.toGetContentsResponseDto(contentsDetailVo);
    }

    @Override
    public ContentsAdminListResponseDto getAllContents(Pageable pageable) {
        Page<ContentsAdminVo> contentsAdminList = contentsHelper.findAllContentsAdminVos(pageable);
        return contentsMapper.toContentsAdminListResponseDto(contentsAdminList);
    }

    @Override
    public ContentsAdminSimpleListResponseDto getAllSimpleContents(ContentsType contentsType) {
        List<ContentsAdminSimpleVo> contentsAdminSimpleList = contentsHelper.findAllContentsAdminSimpleVos(contentsType);
        return contentsMapper.toContentsAdminSimpleListResponseDto(contentsAdminSimpleList);
    }

    @Override
    public void createContents(CreateContentsRequestDto createContentsRequestDto) {
        Contents newContents = contentsMapper.toEntity(createContentsRequestDto);
        contentsHelper.saveContents(newContents);
    }

    @Override
    public void updateContents(Long contentsId, UpdateContentsRequestDto updateContentsRequestDto) {
        Contents contents = contentsHelper.findContentsByIdOrThrow(contentsId);
        contents.updateContents(updateContentsRequestDto);
    }

    @Override
    public void deleteContents(Long contentsId) {
        contentsHelper.deleteContentsById(contentsId);
    }
}
