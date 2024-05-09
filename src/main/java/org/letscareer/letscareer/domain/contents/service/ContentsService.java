package org.letscareer.letscareer.domain.contents.service;

import org.letscareer.letscareer.domain.contents.dto.request.CreateContentsRequestDto;
import org.letscareer.letscareer.domain.contents.dto.response.ContentsAdminListResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ContentsService {
    void createContents(CreateContentsRequestDto createContentsRequestDto);
    ContentsAdminListResponseDto getAllContents(Pageable pageable);
}
