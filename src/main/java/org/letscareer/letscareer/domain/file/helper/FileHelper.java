package org.letscareer.letscareer.domain.file.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.file.entity.File;
import org.letscareer.letscareer.domain.file.repository.FileRepository;
import org.letscareer.letscareer.global.common.utils.S3Utils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Component
public class FileHelper {
    private final FileRepository fileRepository;
    private final S3Utils s3Utils;

    public File createFileAndSave(String filePath, MultipartFile file) {
        File newFile = s3Utils.saveFile(filePath, file);
        return fileRepository.save(newFile);
    }

    public void deleteFile(File file) {
        s3Utils.deleteFile(file.getFileName());
        fileRepository.delete(file);
    }
}
