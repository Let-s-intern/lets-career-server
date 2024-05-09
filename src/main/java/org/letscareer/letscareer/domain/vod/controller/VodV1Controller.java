package org.letscareer.letscareer.domain.vod.controller;


import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.vod.dto.request.CreateVodRequestDto;
import org.letscareer.letscareer.domain.vod.service.VodService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/vod")
@RestController
public class VodV1Controller {
    private final VodService vodService;

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createVodProgram(@RequestBody final CreateVodRequestDto requestDto) {
        vodService.createVod(requestDto);
        return SuccessResponse.created(null);
    }
}
