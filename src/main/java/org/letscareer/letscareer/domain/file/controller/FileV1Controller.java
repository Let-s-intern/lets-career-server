package org.letscareer.letscareer.domain.file.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.file.dto.response.CreateFileResponseDto;
import org.letscareer.letscareer.domain.file.service.FileService;
import org.letscareer.letscareer.domain.file.type.FileType;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/api/v1/file")
@RestController
public class FileV1Controller {
    private final FileService fileService;

    @Operation(summary = "이미지 url 생성", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createFile(@RequestParam final FileType type,
                                                         @RequestPart final MultipartFile file) {
        System.out.println("[upload type]::" + type);
        final CreateFileResponseDto responseDto = fileService.createFile(type, file);
        return SuccessResponse.created(responseDto);
    }
}
