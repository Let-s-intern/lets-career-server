package org.letscareer.letscareer.domain.mission.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.application.vo.UserChallengeApplicationVo;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.attendance.vo.MissionScoreVo;
import org.letscareer.letscareer.domain.user.type.AccountType;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record MissionApplicationScoreResponseDto(
        Long applicationId,
        String name,
        String email,
        String phoneNum,
        List<MissionScoreResponseDto> scores,
        String orderId,
        String couponName,
        Integer finalPrice,
        Integer paybackPrice,
        Boolean isRefunded
) {
    public static MissionApplicationScoreResponseDto of(UserChallengeApplicationVo userChallengeApplicationVo,
                                                        List<MissionScoreResponseDto> scoreResponseDtoList,
                                                        Payment payment,
                                                        Coupon coupon) {
        return MissionApplicationScoreResponseDto.builder()
                .applicationId(userChallengeApplicationVo.id())
                .name(userChallengeApplicationVo.name())
                .email(userChallengeApplicationVo.contactEmail())
                .phoneNum(userChallengeApplicationVo.phoneNum())
                .scores(scoreResponseDtoList)
                .orderId(payment.getOrderId())
                .couponName(coupon != null ? payment.getCoupon().getName() : null)
                .finalPrice(payment.getFinalPrice())
                .paybackPrice(payment.getPaybackPrice())
                .isRefunded(payment.getIsRefunded())
                .build();
    }
}
