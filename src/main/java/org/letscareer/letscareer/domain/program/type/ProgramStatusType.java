package org.letscareer.letscareer.domain.program.type;

import java.time.LocalDateTime;

public enum ProgramStatusType {
    PREV,           // 모집 예정
    PROCEEDING,     // 모집중
    POST;           // 마감

    public static ProgramStatusType of(ProgramType programType, LocalDateTime beginning, LocalDateTime deadline) {
        if(programType.equals(ProgramType.VOD)) return PROCEEDING;

        LocalDateTime now = LocalDateTime.now();
        if(beginning.isAfter(now))
            return PREV;
        else if(beginning.isBefore(now) && deadline.isAfter(now))
            return PROCEEDING;
        else
            return POST;
    }
}
