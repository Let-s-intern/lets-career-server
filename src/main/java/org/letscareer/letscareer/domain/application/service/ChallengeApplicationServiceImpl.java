package org.letscareer.letscareer.domain.application.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.request.CreateApplicationRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service("CHALLENGE")
public class ChallengeApplicationServiceImpl implements ApplicationService {
    @Override
    public void createApplication(Long programId, Long userId, CreateApplicationRequestDto requestDto) {

    }
}
