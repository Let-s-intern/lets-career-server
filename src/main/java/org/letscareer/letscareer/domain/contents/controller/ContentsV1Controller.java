package org.letscareer.letscareer.domain.contents.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.contents.dto.request.CreateContentsRequestDto;
import org.letscareer.letscareer.domain.contents.service.ContentsService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/contents")
@RestController
public class ContentsV1Controller {
    private final ContentsService contentsService;

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createContents(@RequestBody @Valid CreateContentsRequestDto createContentsRequestDto) {
        contentsService.createContents(createContentsRequestDto);
        return SuccessResponse.created(null);
    }
}
