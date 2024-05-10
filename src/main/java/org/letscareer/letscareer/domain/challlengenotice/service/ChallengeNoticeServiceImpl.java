package org.letscareer.letscareer.domain.challlengenotice.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challenge.helper.ChallengeHelper;
import org.letscareer.letscareer.domain.challlengenotice.dto.request.CreateChallengeNoticeRequestDto;
import org.letscareer.letscareer.domain.challlengenotice.entity.ChallengeNotice;
import org.letscareer.letscareer.domain.challlengenotice.helper.ChallengeNoticeHelper;
import org.letscareer.letscareer.domain.challlengenotice.mapper.ChallengeNoticeMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChallengeNoticeServiceImpl implements ChallengeNoticeService {
    private final ChallengeNoticeHelper challengeNoticeHelper;
    private final ChallengeNoticeMapper challengeNoticeMapper;
    private final ChallengeHelper challengeHelper;

    @Override
    public void createChallengeNotice(Long challengeId, CreateChallengeNoticeRequestDto createChallengeNoticeRequestDto) {
        final Challenge challenge = challengeHelper.findChallengeByIdOrThrow(challengeId);
        ChallengeNotice newChallengeNotice = challengeNoticeMapper.toEntity(createChallengeNoticeRequestDto, challenge);
        challengeNoticeHelper.saveChallengeNotice(newChallengeNotice);
    }
}
