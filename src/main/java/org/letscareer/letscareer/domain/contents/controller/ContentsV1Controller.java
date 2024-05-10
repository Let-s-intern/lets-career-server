package org.letscareer.letscareer.domain.contents.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.contents.dto.request.CreateContentsRequestDto;
import org.letscareer.letscareer.domain.contents.dto.request.UpdateContentsRequestDto;
import org.letscareer.letscareer.domain.contents.dto.response.ContentsAdminListResponseDto;
import org.letscareer.letscareer.domain.contents.service.ContentsService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/contents")
@RestController
public class ContentsV1Controller {
    private final ContentsService contentsService;

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createContents(@RequestBody @Valid final CreateContentsRequestDto createContentsRequestDto) {
        contentsService.createContents(createContentsRequestDto);
        return SuccessResponse.created(null);
    }

    @GetMapping("/admin")
    public ResponseEntity<SuccessResponse<?>> getAllContentsForAdmin(@PageableDefault Pageable pageable) {
        final ContentsAdminListResponseDto responseDto = contentsService.getAllContents(pageable);
        return SuccessResponse.ok(responseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> updateContents(@PathVariable final Long id,
                                                             @RequestBody final UpdateContentsRequestDto updateContentsRequestDto) {
        contentsService.updateContents(id, updateContentsRequestDto);
        return SuccessResponse.ok(null);
    }
}
