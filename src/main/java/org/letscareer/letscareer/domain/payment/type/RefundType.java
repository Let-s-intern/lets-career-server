package org.letscareer.letscareer.domain.payment.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.domain.program.type.ProgramType;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RefundType {
    ZERO(0),
    ALL(1),
    TWO_THIRD((double) 1/3),
    HALF((double) 1/2);

    private final double percent;

    public static RefundType of(ProgramType programType, LocalDate startDate, LocalDate endDate) {
        LocalDate now = LocalDate.now();
        if(now.isBefore(startDate)) return ALL;
        if(programType.equals(ProgramType.CHALLENGE)) {
            long challengePeriod = ChronoUnit.DAYS.between(startDate, endDate) + 1;
            LocalDate d3 = startDate.plusDays(challengePeriod/3);
            LocalDate d2 = startDate.plusDays(challengePeriod/2);
            if(now.isBefore(d3)) return TWO_THIRD;
            else if(now.isBefore(d2)) return HALF;
        }
        return ZERO;
    }
}
