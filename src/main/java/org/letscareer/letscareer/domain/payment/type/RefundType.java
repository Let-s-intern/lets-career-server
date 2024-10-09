package org.letscareer.letscareer.domain.payment.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.live.entity.Live;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RefundType {
    ZERO(0),
    ALL(1),
    TWO_THIRD((double) 2 / 3),
    HALF((double) 1 / 2),
    PAYBACK(-1);

    private final double percent;

    public static RefundType ofLive(Live live) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDate = live.getStartDate();
        if (now.isBefore(startDate)) return ALL;
        return ZERO;
    }

    public static RefundType ofChallenge(Challenge challenge) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDate = challenge.getStartDate();
        LocalDateTime endDate = challenge.getEndDate();
        if (now.isBefore(startDate)) return ALL;
        long challengePeriod = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        LocalDateTime d3 = startDate.plusDays((long) Math.ceil((double) challengePeriod / 3));
        LocalDateTime d2 = startDate.plusDays((long) Math.ceil((double) challengePeriod / 2));
        if (now.isBefore(d3)) return TWO_THIRD;
        else if (now.isBefore(d2)) return HALF;
        else return ZERO;
    }
}
