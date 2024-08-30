package org.letscareer.letscareer.domain.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.user.dto.response.UserParticipationResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.mapper.UserMapper;
import org.letscareer.letscareer.domain.user.service.UserParticipationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserParticipationServiceImpl implements UserParticipationService {
    private final UserMapper userMapper;

    @Override
    public UserParticipationResponseDto execute(User user) {
        return userMapper.toUserParticipationResponseDto(user);
    }
}
