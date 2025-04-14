package org.letscareer.letscareer.domain.challengeoption.service;

import org.letscareer.letscareer.domain.challengeoption.dto.request.CreateChallengeOptionRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface ChallengeOptionService {
    void createChallengeOption(CreateChallengeOptionRequestDto requestDto);
}
