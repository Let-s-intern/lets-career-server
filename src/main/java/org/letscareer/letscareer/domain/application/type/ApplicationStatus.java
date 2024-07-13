package org.letscareer.letscareer.domain.application.type;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public enum ApplicationStatus {
    WAITING, IN_PROGRESS, DONE;

    public static ApplicationStatus of(Boolean isCanceled, LocalDateTime programEndDate) {
        if(!isCanceled) return WAITING;
        else if(programEndDate.isAfter(LocalDateTime.now())) return IN_PROGRESS;
        else return DONE;
    }
}
