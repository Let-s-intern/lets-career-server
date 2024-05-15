package org.letscareer.letscareer.domain.application.service;

import org.letscareer.letscareer.domain.application.dto.request.CreateApplicationRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface ApplicationService {

    void createApplication(Long programId, Long userId, CreateApplicationRequestDto requestDto);
}
