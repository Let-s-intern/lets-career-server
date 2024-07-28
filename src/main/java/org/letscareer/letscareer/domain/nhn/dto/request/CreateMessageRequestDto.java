package org.letscareer.letscareer.domain.nhn.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record CreateMessageRequestDto(
        @NotNull String senderKey,
        @NotNull String templateCode,
        List<RecipientInfo<?>> recipientList
) {
    public static CreateMessageRequestDto of(String senderKey,
                                             String templateCode,
                                             List<RecipientInfo<?>> recipientList) {
        return CreateMessageRequestDto.builder()
                .senderKey(senderKey)
                .templateCode(templateCode)
                .recipientList(recipientList)
                .build();
    }
}
