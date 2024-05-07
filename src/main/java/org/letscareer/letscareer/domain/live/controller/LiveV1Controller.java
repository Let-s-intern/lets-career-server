package org.letscareer.letscareer.domain.live.controller;


import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.live.dto.request.CreateLiveRequestDto;
import org.letscareer.letscareer.domain.live.service.LiveService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/live")
@RestController
public class LiveV1Controller {
    private final LiveService liveService;

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createLiveProgram(@RequestBody final CreateLiveRequestDto requestDto) {
        liveService.createLiveService(requestDto);
        return SuccessResponse.created(null);
    }
}
