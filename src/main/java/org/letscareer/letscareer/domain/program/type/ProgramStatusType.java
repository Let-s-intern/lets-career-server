package org.letscareer.letscareer.domain.program.type;

import java.time.LocalDateTime;

public enum ProgramStatusType {
    PREV,
    PROCEEDING,
    POST;

    public static ProgramStatusType of(LocalDateTime startDate, LocalDateTime endDate) {
        LocalDateTime now = LocalDateTime.now();
        if(startDate.isAfter(now))
            return PREV;
        else if(startDate.isBefore(now) && endDate.isAfter(now))
            return PROCEEDING;
        else
            return POST;
    }
}
