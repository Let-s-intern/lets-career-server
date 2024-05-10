package org.letscareer.letscareer.domain.challlengenotice.service;

import org.letscareer.letscareer.domain.challlengenotice.dto.request.CreateChallengeNoticeRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface ChallengeNoticeService {
    void createChallengeNotice(Long challengeId, CreateChallengeNoticeRequestDto createChallengeNoticeRequestDto);
}
