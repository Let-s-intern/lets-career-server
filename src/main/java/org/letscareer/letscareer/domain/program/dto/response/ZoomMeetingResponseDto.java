package org.letscareer.letscareer.domain.program.dto.response;

import lombok.Builder;

@Builder
public record ZoomMeetingResponseDto(
        String host_email,
        String id,
        String created_at,
        String join_url,
        String start_time,
        String password,
        String agenda,
        String duration,
        String timezone,
        String topic,
        Integer type
) {
}
