package org.letscareer.letscareer.domain.nhn.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record LiveClassRemindParameter(
        String userName,
        String programTitle,
        @JsonFormat(pattern = "yyyy년 MM월 dd일", timezone = "Asia/Seoul")
        LocalDateTime programStartDate,
        String zoomLink,
        Long programId,
        String link,
        String password
) {
        public static LiveClassRemindParameter of(String userName,
                                                  String programTitle,
                                                  LocalDateTime programStartDate,
                                                  String zoomLink,
                                                  Long programId,
                                                  String link,
                                                  String password) {
                return LiveClassRemindParameter.builder()
                        .userName(userName)
                        .programTitle(programTitle)
                        .programStartDate(programStartDate)
                        .zoomLink(zoomLink)
                        .programId(programId)
                        .link(link)
                        .password(password)
                        .build();
        }
}
