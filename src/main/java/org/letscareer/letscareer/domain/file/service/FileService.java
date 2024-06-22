package org.letscareer.letscareer.domain.file.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.file.dto.response.CreateFileResponseDto;
import org.letscareer.letscareer.domain.file.entity.File;
import org.letscareer.letscareer.domain.file.helper.FileHelper;
import org.letscareer.letscareer.domain.file.mapper.FileMapper;
import org.letscareer.letscareer.domain.file.type.FileType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class FileService {
    private final FileHelper fileHelper;
    private final FileMapper fileMapper;

    public CreateFileResponseDto createFile(FileType type, MultipartFile file) {
        File newFile = fileHelper.createFileAndSave(type.getDesc(), file);
        return fileMapper.toCreateFileResponseDto(newFile.getUrl());
    }
}
