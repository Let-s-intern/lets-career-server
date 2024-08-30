package org.letscareer.letscareer.domain.nhn.dto.request.challenge;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.mission.entity.Mission;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record MissionEndParameter(
        String userName,
        Integer th,
        String programTitle,
        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm", timezone = "Asia/Seoul")
        LocalDateTime missionStartDate,
        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm", timezone = "Asia/Seoul")
        LocalDateTime missionEndDate,
        Long programId
) {
    public static MissionEndParameter of(String userName,
                                         Mission mission,
                                         Challenge challenge) {
        return MissionEndParameter.builder()
                .userName(userName)
                .th(mission.getTh())
                .programTitle(challenge.getTitle())
                .missionStartDate(mission.getStartDate())
                .missionEndDate(mission.getEndDate())
                .programId(challenge.getId())
                .build();
    }
}
