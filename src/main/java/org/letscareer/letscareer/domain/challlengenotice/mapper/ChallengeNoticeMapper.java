package org.letscareer.letscareer.domain.challlengenotice.mapper;

import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challlengenotice.dto.request.CreateChallengeNoticeRequestDto;
import org.letscareer.letscareer.domain.challlengenotice.dto.response.ChallengeNoticeAdminListResponseDto;
import org.letscareer.letscareer.domain.challlengenotice.entity.ChallengeNotice;
import org.letscareer.letscareer.domain.challlengenotice.vo.ChallengeNoticeAdminVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChallengeNoticeMapper {
    public ChallengeNotice toEntity(CreateChallengeNoticeRequestDto createChallengeNoticeRequestDto, Challenge challenge) {
        return ChallengeNotice.createChallengeNotice(createChallengeNoticeRequestDto, challenge);
    }

    public ChallengeNoticeAdminListResponseDto toChallengeNoticeAdminListResponseDto(List<ChallengeNoticeAdminVo> challengeNoticeAdminList) {
        return ChallengeNoticeAdminListResponseDto.of(challengeNoticeAdminList);
    }
}
