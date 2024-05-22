package org.letscareer.letscareer.domain.application.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.application.repository.ApplicationRepository;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import static org.letscareer.letscareer.domain.application.error.ApplicationErrorCode.APPLICATION_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class ApplicationHelper {
    private final ApplicationRepository applicationRepository;

    public Application findByIdOrThrow(Long applicationId) {
        return applicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException(APPLICATION_NOT_FOUND));
    }
}
