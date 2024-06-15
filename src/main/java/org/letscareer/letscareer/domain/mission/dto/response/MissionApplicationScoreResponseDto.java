package org.letscareer.letscareer.domain.mission.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.application.vo.UserChallengeApplicationVo;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceScoreVo;
import org.letscareer.letscareer.domain.user.type.AccountType;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record MissionApplicationScoreResponseDto(
        Long applicationId,
        String name,
        String email,
        String phoneNum,
        String accountNum,
        AccountType accountType,
        List<AttendanceScoreVo> scores,
        Boolean isRefunded
) {
    public static MissionApplicationScoreResponseDto of(UserChallengeApplicationVo userChallengeApplicationVo,
                                                        List<AttendanceScoreVo> scores,
                                                        Payment payment) {
        return MissionApplicationScoreResponseDto.builder()
                .applicationId(userChallengeApplicationVo.id())
                .name(userChallengeApplicationVo.name())
                .email(userChallengeApplicationVo.contactEmail())
                .phoneNum(userChallengeApplicationVo.phoneNum())
                .accountNum(userChallengeApplicationVo.accountNum())
                .accountType(userChallengeApplicationVo.accountType())
                .scores(scores)
                .isRefunded(payment.getIsRefunded())
                .build();
    }
}
