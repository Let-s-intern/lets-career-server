package org.letscareer.letscareer.domain.application.service;

import org.letscareer.letscareer.domain.application.dto.request.CreateApplicationRequestDto;
import org.letscareer.letscareer.domain.application.dto.request.UpdateApplicationRequestDto;
import org.letscareer.letscareer.domain.application.dto.response.CreateApplicationResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface ApplicationService {
    CreateApplicationResponseDto createApplication(Long programId, User user, CreateApplicationRequestDto requestDto);

    void updateApplication(Long applicationId, User user, UpdateApplicationRequestDto requestDto);

    void cancelApplication(Long applicationId, User user);
}
