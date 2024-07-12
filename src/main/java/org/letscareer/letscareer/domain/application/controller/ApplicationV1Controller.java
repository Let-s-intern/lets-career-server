package org.letscareer.letscareer.domain.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.request.CreateApplicationRequestDto;
import org.letscareer.letscareer.domain.application.dto.response.CreateApplicationResponseDto;
import org.letscareer.letscareer.domain.application.service.ApplicationServiceFactory;
import org.letscareer.letscareer.domain.application.service.TossApplicationServiceFactory;
import org.letscareer.letscareer.domain.application.type.TossProgramType;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.annotation.ApiErrorCode;
import org.letscareer.letscareer.global.common.annotation.CurrentUser;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.letscareer.letscareer.global.common.entity.SwaggerEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/application")
@RestController
public class ApplicationV1Controller {
    private final ApplicationServiceFactory applicationServiceFactory;
    private final TossApplicationServiceFactory tossApplicationServiceFactory;

    @Operation(summary = "신청서 생성", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CreateApplicationResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.INVALID_APPLICATION_TIME})
    @PostMapping("/{programId}")
    public ResponseEntity<SuccessResponse<?>> createApplication(@PathVariable final Long programId,
                                                                @RequestParam final ProgramType type,
                                                                @CurrentUser final User user,
                                                                @RequestBody final CreateApplicationRequestDto requestDto) {
        if (user.getId().equals(1017L))
            tossApplicationServiceFactory.getTossApplicationService(TossProgramType.parseTossProgramType(programType)).createApplication(programId, user, requestDto);
        else
            applicationServiceFactory.getApplicationService(programType).createApplication(programId, user, requestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "신청서 삭제", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @DeleteMapping("/{applicationId}")
    public ResponseEntity<SuccessResponse<?>> deleteApplication(@PathVariable final Long applicationId,
                                                                @RequestParam final ProgramType type,
                                                                @CurrentUser User user) {
        applicationServiceFactory.getApplicationService(type).deleteApplication(applicationId, user);
        return SuccessResponse.ok(null);
    }
}