package org.letscareer.letscareer.domain.contents.service;

import org.letscareer.letscareer.domain.contents.dto.request.CreateContentsRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface ContentsService {
    void createContents(CreateContentsRequestDto createContentsRequestDto);
}
