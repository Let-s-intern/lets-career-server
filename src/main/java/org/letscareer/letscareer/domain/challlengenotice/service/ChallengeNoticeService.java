package org.letscareer.letscareer.domain.challlengenotice.service;

import org.letscareer.letscareer.domain.challlengenotice.dto.request.CreateChallengeNoticeRequestDto;
import org.letscareer.letscareer.domain.challlengenotice.dto.response.ChallengeNoticeAdminListResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface ChallengeNoticeService {
    void createChallengeNotice(Long challengeId, CreateChallengeNoticeRequestDto createChallengeNoticeRequestDto);

    ChallengeNoticeAdminListResponseDto getChallengeNoticesForAdmin(Long challengeId);

    void updateChallengeNotice(Long challengeNoticeId, CreateChallengeNoticeRequestDto updateChallengeNoticeRequestDto);
}
