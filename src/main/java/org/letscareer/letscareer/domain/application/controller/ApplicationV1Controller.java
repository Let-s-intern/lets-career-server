package org.letscareer.letscareer.domain.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.request.CreateApplicationRequestDto;
import org.letscareer.letscareer.domain.application.dto.response.GetMyApplicationsResponseDto;
import org.letscareer.letscareer.domain.application.service.ApplicationServiceFactory;
import org.letscareer.letscareer.domain.application.service.ApplicationSpecificService;
import org.letscareer.letscareer.domain.application.type.ApplicationReviewStatus;
import org.letscareer.letscareer.domain.application.type.ApplicationStatus;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.annotation.CurrentUser;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/application")
@RestController
public class ApplicationV1Controller {
    private final ApplicationServiceFactory applicationServiceFactory;
    private final ApplicationSpecificService applicationSpecificService;

    @Operation(summary = "신청서 생성", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @PostMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> createApplication(@PathVariable(name = "id") final Long programId,
                                                                @RequestParam(name = "type") final ProgramType programType,
                                                                @CurrentUser final User user,
                                                                @RequestBody final CreateApplicationRequestDto requestDto) {
        applicationServiceFactory.getApplicationService(programType).createApplication(programId, user, requestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "나의 신청서 전체 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetMyApplicationsResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getMyApplications(@CurrentUser User user,
                                                                @RequestParam(required = false) final ApplicationStatus status,
                                                                @RequestParam(required = false) final ApplicationReviewStatus reviewStatus) {
        GetMyApplicationsResponseDto responseDto = applicationSpecificService.getMyApplications(user, status, reviewStatus);
        return SuccessResponse.ok(responseDto);
    }


    @Operation(summary = "신청서 삭제", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> deleteApplication(@PathVariable(name = "id") final Long applicationId,
                                                                @RequestParam final ProgramType type,
                                                                @CurrentUser User user) {
        applicationServiceFactory.getApplicationService(type).deleteApplication(applicationId, user);
        return SuccessResponse.ok(null);
    }
}