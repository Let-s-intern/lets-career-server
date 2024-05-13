package org.letscareer.letscareer.domain.application.mapper;

import org.letscareer.letscareer.domain.application.dto.response.GetChallengeApplicationsResponseDto;
import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChallengeApplicationMapper {

    public GetChallengeApplicationsResponseDto toGetChallengeApplicationsResponseDto(List<AdminChallengeApplicationVo> vos) {
        return GetChallengeApplicationsResponseDto.of(vos);
    }
}
