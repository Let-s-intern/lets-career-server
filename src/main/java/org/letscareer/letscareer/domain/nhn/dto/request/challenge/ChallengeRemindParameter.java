package org.letscareer.letscareer.domain.nhn.dto.request.challenge;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record ChallengeRemindParameter(
        String userName,
        String programTitle,
        @JsonFormat(pattern = "yyyy년 MM월 dd일", timezone = "Asia/Seoul")
        LocalDateTime programStartDate,
        @JsonFormat(pattern = "yyyy년 MM월 dd일", timezone = "Asia/Seoul")
        LocalDateTime programEndDate,
        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm", timezone = "Asia/Seoul")
        LocalDateTime programOtStart,
        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm", timezone = "Asia/Seoul")
        LocalDateTime programOtEnd,
        String chatLink,
        String chatPassword,
        String zoomLink
        ) {
    public static ChallengeRemindParameter of(String userName,
                                              Challenge challenge) {
        return ChallengeRemindParameter.builder()
                .userName(userName)
                .programTitle(challenge.getTitle())
                .programStartDate(challenge.getStartDate())
                .programEndDate(challenge.getEndDate())
                .programOtStart(challenge.getStartDate())
                .programOtEnd(challenge.getStartDate().plusMinutes(40))
                .chatLink(challenge.getChatLink())
                .chatPassword(challenge.getChatPassword())
                .zoomLink(challenge.getZoomLink().substring(8))
                .build();
    }
}
