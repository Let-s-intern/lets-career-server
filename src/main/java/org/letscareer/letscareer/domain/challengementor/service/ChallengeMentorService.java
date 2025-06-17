package org.letscareer.letscareer.domain.challengementor.service;

import org.letscareer.letscareer.domain.challengementor.dto.response.GetMyChallengeMentorsResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface ChallengeMentorService {
    GetMyChallengeMentorsResponseDto getMyChallengeMentors(User mentor);
    void deleteChallengeMentor(Long challengeMentorId);
}
