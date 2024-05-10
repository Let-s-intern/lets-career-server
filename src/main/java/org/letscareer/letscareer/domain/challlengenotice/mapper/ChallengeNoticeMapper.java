package org.letscareer.letscareer.domain.challlengenotice.mapper;

import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challlengenotice.dto.request.CreateChallengeNoticeRequestDto;
import org.letscareer.letscareer.domain.challlengenotice.entity.ChallengeNotice;
import org.springframework.stereotype.Component;

@Component
public class ChallengeNoticeMapper {
    public ChallengeNotice toEntity(CreateChallengeNoticeRequestDto createChallengeNoticeRequestDto, Challenge challenge) {
        return ChallengeNotice.createChallengeNotice(createChallengeNoticeRequestDto, challenge);
    }
}
