package org.letscareer.letscareer.domain.nhn.dto.response;

public record HeaderInfo(
        Integer resultCode,
        String resultMessage,
        Boolean isSuccessful
) {
}
