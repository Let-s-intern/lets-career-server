package org.letscareer.letscareer.domain.user.service;

import org.letscareer.letscareer.domain.user.dto.response.UserParticipationResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;

public interface UserParticipationService {
    UserParticipationResponseDto execute(User user);
}
