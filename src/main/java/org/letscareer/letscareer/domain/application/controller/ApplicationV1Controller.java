package org.letscareer.letscareer.domain.application.controller;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.service.ApplicationServiceFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/application")
@RestController
public class ApplicationV1Controller {
    private final ApplicationServiceFactory applicationServiceFactory;
}