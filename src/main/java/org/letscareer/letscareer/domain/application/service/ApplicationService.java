package org.letscareer.letscareer.domain.application.service;

import org.letscareer.letscareer.domain.application.dto.request.CreateApplicationRequestDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface ApplicationService {
    void createApplication(Long programId, User user, CreateApplicationRequestDto requestDto);

    void deleteApplication(Long applicationId, User user);
}
