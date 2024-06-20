package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeApplicationFormVo;
import org.letscareer.letscareer.domain.price.vo.ChallengePriceDetailVo;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeApplicationFormResponseDto(
        Boolean applied,
        String name,
        String email,
        String contactEmail,
        String phoneNumber,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime deadline,
        ProgramStatusType statusType,
        List<ChallengePriceDetailVo> priceList
) {
    public static GetChallengeApplicationFormResponseDto of(User user,
                                                            Boolean applied,
                                                            ChallengeApplicationFormVo programInfo,
                                                            List<ChallengePriceDetailVo> challengePriceDetailVos) {
        return GetChallengeApplicationFormResponseDto.builder()
                .applied(applied)
                .name(Objects.isNull(user) ? null : user.getName())
                .email(Objects.isNull(user) ? null : user.getEmail())
                .contactEmail(Objects.isNull(user) ? null : user.getContactEmail())
                .phoneNumber(Objects.isNull(user) ? null : user.getPhoneNum())
                .startDate(programInfo.startDate())
                .endDate(programInfo.endDate())
                .deadline(programInfo.deadline())
                .statusType(programInfo.statusType())
                .priceList(challengePriceDetailVos)
                .build();
    }
}
