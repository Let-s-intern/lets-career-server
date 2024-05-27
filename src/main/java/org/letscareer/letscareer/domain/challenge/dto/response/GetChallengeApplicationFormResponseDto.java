package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeApplicationFormVo;
import org.letscareer.letscareer.domain.price.vo.ChallengePriceDetailVo;
import org.letscareer.letscareer.domain.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeApplicationFormResponseDto(
        String name,
        String email,
        String phoneNumber,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime deadline,
        List<ChallengePriceDetailVo> challengePriceDetailVos
) {
    public static GetChallengeApplicationFormResponseDto of(User user,
                                                            ChallengeApplicationFormVo programInfo,
                                                            List<ChallengePriceDetailVo> challengePriceDetailVos) {
        return GetChallengeApplicationFormResponseDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNum())
                .startDate(programInfo.startDate())
                .endDate(programInfo.endDate())
                .deadline(programInfo.deadline())
                .challengePriceDetailVos(challengePriceDetailVos)
                .build();
    }
}
