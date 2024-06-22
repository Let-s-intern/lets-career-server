package org.letscareer.letscareer.domain.program.dto.request;

import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record CreateZoomMeetingRequestDto(
        String agenda,
        Boolean default_password,
        Integer duration,
        String start_time,
        String timezone,
        String topic,
        Integer type
) {
    public static CreateZoomMeetingRequestDto of(String agenda, Integer duration, LocalDateTime start_time, String topic) {
        return CreateZoomMeetingRequestDto.builder()
                .agenda(agenda)
                .default_password(true)
                .duration(duration)
                .start_time(start_time.toString() + ":00")
                .timezone("Asia/Seoul")
                .topic(topic)
                .type(2)
                .build();
    }
}
