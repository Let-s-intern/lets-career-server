package org.letscareer.letscareer.domain.contents.service;

import org.letscareer.letscareer.domain.contents.dto.request.CreateContentsRequestDto;
import org.letscareer.letscareer.domain.contents.dto.request.UpdateContentsRequestDto;
import org.letscareer.letscareer.domain.contents.dto.response.ContentsAdminListResponseDto;
import org.letscareer.letscareer.domain.contents.dto.response.ContentsAdminSimpleListResponseDto;
import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ContentsService {
    ContentsAdminListResponseDto getAllContents(Pageable pageable);

    ContentsAdminSimpleListResponseDto getAllSimpleContents(ContentsType contentsType);

    void createContents(CreateContentsRequestDto createContentsRequestDto);

    void updateContents(Long contentsId, UpdateContentsRequestDto updateContentsRequestDto);

    void deleteContents(Long contentsId);
}
