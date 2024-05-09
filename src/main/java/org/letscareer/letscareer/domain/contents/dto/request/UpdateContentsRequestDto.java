package org.letscareer.letscareer.domain.contents.dto.request;

import org.letscareer.letscareer.domain.contents.type.ContentsType;

public record UpdateContentsRequestDto(
        ContentsType type,
        String title,
        String link
) {
}
