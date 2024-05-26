package org.letscareer.letscareer.domain.program.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.program.dto.request.GetProgramsForDurationRequestDto;
import org.letscareer.letscareer.domain.program.dto.response.GetProgramsForDurationResponseDto;
import org.letscareer.letscareer.domain.program.dto.response.GetProgramsResponseDto;
import org.letscareer.letscareer.domain.program.service.ProgramService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/api/v1/program")
@RestController
public class ProgramV1Controller {
    private final ProgramService programService;

    @Operation(summary = "프로그램 통합 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetProgramsResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getPrograms(final Pageable pageable) {
        final GetProgramsResponseDto responseDto = programService.getPrograms(pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "프로그램 통합 조회 - 기간별", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetProgramsForDurationResponseDto.class)))
    })
    @GetMapping("/duration")
    public ResponseEntity<SuccessResponse<?>> getProgramsForDuration(@RequestParam final GetProgramsForDurationRequestDto requestDto) {
        final GetProgramsForDurationResponseDto responseDto = programService.getProgramsForDuration(requestDto);
        return SuccessResponse.ok(responseDto);
    }
}
