package org.letscareer.letscareer.domain.program.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.program.dto.response.GetProgramForAdminResponseDto;
import org.letscareer.letscareer.domain.program.dto.response.GetProgramsForAdminResponseDto;
import org.letscareer.letscareer.domain.program.dto.response.GetProgramsForConditionResponseDto;
import org.letscareer.letscareer.domain.program.dto.response.GetProgramsForRecommendResponseDto;
import org.letscareer.letscareer.domain.program.service.ProgramService;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/api/v1/program")
@RestController
public class ProgramV1Controller {
    private final ProgramService programService;

    @Operation(summary = "프로그램 통합 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetProgramsForConditionResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getProgramsForCondition(@RequestParam(required = false) final List<ProgramType> type,
                                                                      @RequestParam(required = false) final List<ProgramClassification> classification,
                                                                      @RequestParam(required = false) final List<ProgramStatusType> status,
                                                                      @RequestParam(required = false) final LocalDateTime startDate,
                                                                      @RequestParam(required = false) final LocalDateTime endDate,
                                                                      final Pageable pageable) {
        final GetProgramsForConditionResponseDto responseDto = programService.getProgramsForCondition(type, classification, status, startDate, endDate, pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 프로그램 통합 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetProgramForAdminResponseDto.class)))
    })
    @GetMapping("/admin")
    public ResponseEntity<SuccessResponse<?>> getProgramsForAdmin(@RequestParam(required = false) final List<ProgramType> type,
                                                                  @RequestParam(required = false) final List<ProgramClassification> classification,
                                                                  @RequestParam(required = false) final List<ProgramStatusType> status,
                                                                  @RequestParam(required = false) final LocalDateTime startDate,
                                                                  @RequestParam(required = false) final LocalDateTime endDate,
                                                                  final Pageable pageable) {
        final GetProgramsForAdminResponseDto responseDto = programService.getProgramsForAdmin(type, classification, status, startDate, endDate, pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "프로그램 추천 통합 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetProgramsForRecommendResponseDto.class)))
    })
    @GetMapping("/recommend")
    public ResponseEntity<SuccessResponse<?>> getProgramsForCondition() {
        final GetProgramsForRecommendResponseDto responseDto = programService.getProgramsForRecommend();
        return SuccessResponse.ok(responseDto);
    }
}
