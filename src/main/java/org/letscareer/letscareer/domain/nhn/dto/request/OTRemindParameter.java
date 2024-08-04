package org.letscareer.letscareer.domain.nhn.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record OTRemindParameter(
        String userName,
        String programTitle,
        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm", timezone = "Asia/Seoul")
        LocalDateTime programStartDate,
        String zoomLink,
        Long programId
) {
    public static OTRemindParameter of(String userName,
                                       Challenge challenge) {
        return OTRemindParameter.builder()
                .userName(userName)
                .programTitle(challenge.getTitle())
                .programStartDate(challenge.getStartDate())
                .zoomLink(challenge.getZoomLink())
                .programId(challenge.getId())
                .build();
    }
}
