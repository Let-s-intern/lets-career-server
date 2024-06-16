package org.letscareer.letscareer.domain.score.mapper;

import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengeTotalScoreResponseDto;
import org.springframework.stereotype.Component;

@Component
public class AttendanceScoreMapper {
    public GetChallengeTotalScoreResponseDto toGetChallengeTotalScoreResponseDto(Integer totalScore) {
        return GetChallengeTotalScoreResponseDto.of(totalScore);
    }
}
