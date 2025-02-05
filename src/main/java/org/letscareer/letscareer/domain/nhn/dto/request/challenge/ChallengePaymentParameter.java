package org.letscareer.letscareer.domain.nhn.dto.request.challenge;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record ChallengePaymentParameter(
        String userName,
        String programTitle,
        @JsonFormat(pattern = "yyyy년 MM월 dd일", timezone = "Asia/Seoul")
        LocalDateTime programStartDate,
        @JsonFormat(pattern = "yyyy년 MM월 dd일", timezone = "Asia/Seoul")
        LocalDateTime programEndDate,
        String chatLink,
        String chatPassword,
        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm", timezone = "Asia/Seoul")
        LocalDateTime programOtStart,
        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm", timezone = "Asia/Seoul")
        LocalDateTime programOtEnd,
        String zoomLink,
        Long programId
        ) {
    public static ChallengePaymentParameter of(String userName,
                                               Challenge challenge) {
        return ChallengePaymentParameter.builder()
                .userName(userName)
                .programTitle(challenge.getTitle())
                .programStartDate(challenge.getStartDate())
                .programEndDate(challenge.getEndDate())
                .chatLink(challenge.getChatLink())
                .chatPassword(challenge.getChatPassword())
                .programOtStart(challenge.getStartDate())
                .programOtEnd(challenge.getStartDate().plusMinutes(40))
                .zoomLink(challenge.getZoomLink().substring(8))
                .programId(challenge.getId())
                .build();
    }
}
