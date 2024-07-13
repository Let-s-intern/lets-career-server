package org.letscareer.letscareer.domain.payment.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.live.entity.Live;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RefundType {
    ZERO(0),
    ALL(1),
    TWO_THIRD((double) 2 / 3),
    HALF((double) 1 / 2);

    private final double percent;

    public static RefundType ofLive(Live live) {
        LocalDate now = LocalDate.now();
        LocalDate startDate = live.getStartDate().toLocalDate();
        if (now.isBefore(startDate)) return ALL;
        return ZERO;
    }

    public static RefundType ofChallenge(Challenge challenge) {
        LocalDate now = LocalDate.now();
        LocalDate startDate = challenge.getStartDate().toLocalDate();
        LocalDate endDate = challenge.getEndDate().toLocalDate();
        long challengePeriod = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        LocalDate d3 = startDate.plusDays(challengePeriod / 3);
        LocalDate d2 = startDate.plusDays(challengePeriod / 2);
        if (now.isBefore(d3)) return TWO_THIRD;
        else if (now.isBefore(d2)) return HALF;
        else return ZERO;
    }
}
