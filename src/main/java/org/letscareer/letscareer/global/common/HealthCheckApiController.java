package org.letscareer.letscareer.global.common;

import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckApiController {
    @RequestMapping
    public ResponseEntity<SuccessResponse<?>> healthCheckApi() {
        return SuccessResponse.ok("healthCheck");
    }
}

