package org.letscareer.letscareer.domain.file.mapper;

import org.letscareer.letscareer.domain.file.dto.response.CreateFileResponseDto;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {
    public CreateFileResponseDto toCreateFileResponseDto(String url) {
        return CreateFileResponseDto.of(url);
    }
}
