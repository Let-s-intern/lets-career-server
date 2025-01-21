package org.letscareer.letscareer.global.common.utils.aws;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.file.entity.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Component
@RequiredArgsConstructor
public class S3Utils {
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public File saveFile(String filePath, MultipartFile file) {
        try {
            String originalFileName = file.getOriginalFilename();
            if(filePath != null) originalFileName = filePath + originalFileName;
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());
            amazonS3.putObject(bucket, originalFileName, file.getInputStream(), metadata);
            return File.createFile(originalFileName, amazonS3.getUrl(bucket, originalFileName).toString());

        } catch (SdkClientException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File saveImgFromUrl(String imgUrl, String filePath, String fileName) {
        try {
            String originalFileName = filePath + fileName;
            URL url = new URL(imgUrl);
            InputStream inputStream = url.openStream();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("image/jpeg");
            amazonS3.putObject(bucket, originalFileName, inputStream, metadata);
            return File.createFile(originalFileName, amazonS3.getUrl(bucket, originalFileName).toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFile(String originalFileName) {
        amazonS3.deleteObject(bucket, originalFileName);
    }
}
