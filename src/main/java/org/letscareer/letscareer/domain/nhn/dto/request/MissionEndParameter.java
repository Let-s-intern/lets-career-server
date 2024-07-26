package org.letscareer.letscareer.domain.nhn.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record MissionEndParameter(
        String userName,
        String th,
        String programTitle,
        @JsonFormat(pattern = "yyyy년 MM월 dd일", timezone = "Asia/Seoul")
        LocalDateTime missionStartDate,
        @JsonFormat(pattern = "yyyy년 MM월 dd일", timezone = "Asia/Seoul")
        LocalDateTime missionEndDate,
        Long programId
) {
    public static MissionEndParameter of(String userName,
                                         String th,
                                         String programTitle,
                                         LocalDateTime missionStartDate,
                                         LocalDateTime missionEndDate,
                                         Long programId) {
        return MissionEndParameter.builder()
                .userName(userName)
                .th(th)
                .programTitle(programTitle)
                .missionStartDate(missionStartDate)
                .missionEndDate(missionEndDate)
                .programId(programId)
                .build();
    }
}
