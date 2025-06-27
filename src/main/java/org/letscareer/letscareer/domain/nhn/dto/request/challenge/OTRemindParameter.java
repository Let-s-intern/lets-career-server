package org.letscareer.letscareer.domain.nhn.dto.request.challenge;

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
        LocalDateTime programOtStart,
        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm", timezone = "Asia/Seoul")
        LocalDateTime programOtEnd,
        String chatLink,
        String chatPassword,
        String zoomLink,
        Long programId,
        Long applicationId

) {
    public static OTRemindParameter of(String userName,
                                       Challenge challenge,
                                       Long applicationId) {
        return OTRemindParameter.builder()
                .userName(userName)
                .programTitle(challenge.getTitle())
                .programOtStart(challenge.getStartDate())
                .programOtEnd(challenge.getStartDate().plusMinutes(40))
                .chatLink(challenge.getChatLink())
                .chatPassword(challenge.getChatPassword())
                .zoomLink(challenge.getZoomLink().substring(8))
                .programId(challenge.getId())
                .applicationId(applicationId)
                .build();
    }
}
