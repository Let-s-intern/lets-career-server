package org.letscareer.letscareer.domain.application.controller;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.request.CreateApplicationRequestDto;
import org.letscareer.letscareer.domain.application.service.ApplicationServiceFactory;
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

    @PostMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> createApplication(@PathVariable(name = "id") final Long programId,
                                                                @RequestParam(name = "type") final ProgramType programType,
                                                                @CurrentUser final User user,
                                                                @RequestBody final CreateApplicationRequestDto requestDto) {
        applicationServiceFactory.getApplicationService(programType).createApplication(programId, user.getId(), requestDto);
        return SuccessResponse.created(null);
    }
}