package org.letscareer.letscareer.domain.contents.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.contents.dto.request.CreateContentsRequestDto;
import org.letscareer.letscareer.domain.contents.helper.ContentsHelper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ContentsServiceImpl implements ContentsService {
    private final ContentsHelper contentsHelper;

    @Override
    public void createContents(CreateContentsRequestDto createContentsRequestDto) {
        contentsHelper.createContentsAndSave(createContentsRequestDto);
    }
}
