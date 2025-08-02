package org.letscareer.letscareer.domain.nhn.dto.request.challenge;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record ChallengeEventParameter(
        String userName,
        String programTitle,
        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm", timezone = "Asia/Seoul")
        LocalDateTime missionEndDate,
        Long programId,
        Long applicationId,
        Long missionId
) {
    public static ChallengeEventParameter of(String userName,
                                             String programTitle,
                                             LocalDateTime missionEndDate,
                                             Long programId,
                                             Long applicationId,
                                             Long missionId) {
        return ChallengeEventParameter.builder()
                .userName(userName)
                .programTitle(programTitle)
                .missionEndDate(missionEndDate)
                .programId(programId)
                .applicationId(applicationId)
                .missionId(missionId)
                .build();
    }
}
