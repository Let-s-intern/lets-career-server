package org.letscareer.letscareer.domain.contents.mapper;

import org.letscareer.letscareer.domain.contents.dto.request.CreateContentsRequestDto;
import org.letscareer.letscareer.domain.contents.entity.Contents;
import org.springframework.stereotype.Component;

@Component
public class ContentsMapper {
    public Contents toEntity(CreateContentsRequestDto createContentsRequestDto) {
        return Contents.createContents(createContentsRequestDto);
    }
}
