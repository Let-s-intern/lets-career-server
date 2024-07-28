package org.letscareer.letscareer.domain.nhn.dto.response;

import lombok.Builder;

@Builder
public record CreateMessageResponseDto(
        HeaderInfo header,
        MessageInfo message
) {
}
