package org.letscareer.letscareer.domain.nhn.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateMessageRequestDto(
        @NotNull String senderKey,
        @NotNull String templateCode,
        List<RecipientInfo> recipientList
) {
}
