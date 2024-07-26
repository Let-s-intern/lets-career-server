package org.letscareer.letscareer.domain.nhn.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record ChallengeRemindParameter(
        String userName,
        String programTitle,
        @JsonFormat(pattern = "yyyy년 MM월 dd일", timezone = "Asia/Seoul")
        LocalDateTime programStartDate,
        @JsonFormat(pattern = "yyyy년 MM월 dd일", timezone = "Asia/Seoul")
        LocalDateTime programEndDate,
        String zoomLink,
        String link,
        String password
) {
    public static ChallengeRemindParameter of(String userName,
                                              String programTitle,
                                              LocalDateTime programStartDate,
                                              LocalDateTime programEndDate,
                                              String zoomLink,
                                              String link,
                                              String password) {
        return ChallengeRemindParameter.builder()
                .userName(userName)
                .programTitle(programTitle)
                .programStartDate(programStartDate)
                .programEndDate(programEndDate)
                .zoomLink(zoomLink)
                .link(link)
                .password(password)
                .build();
    }
}
