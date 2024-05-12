package org.letscareer.letscareer.domain.challengeguide.mapper;

import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challengeguide.dto.request.CreateChallengeGuideRequestDto;
import org.letscareer.letscareer.domain.challengeguide.dto.response.ChallengeGuideAdminListResponseDto;
import org.letscareer.letscareer.domain.challengeguide.entity.ChallengeGuide;
import org.letscareer.letscareer.domain.challengeguide.vo.ChallengeGuideAdminVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChallengeGuideMapper {
    public ChallengeGuide toEntity(Challenge challenge, CreateChallengeGuideRequestDto createChallengeGuideRequestDto) {
        return ChallengeGuide.of(challenge, createChallengeGuideRequestDto);
    }

    public ChallengeGuideAdminListResponseDto toChallengeGuideAdminListResponseDto(List<ChallengeGuideAdminVo> challengeGuideAdminList) {
        return ChallengeGuideAdminListResponseDto.of(challengeGuideAdminList);
    }
}
