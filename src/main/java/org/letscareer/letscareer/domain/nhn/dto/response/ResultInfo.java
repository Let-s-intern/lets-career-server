package org.letscareer.letscareer.domain.nhn.dto.response;

public record ResultInfo(
        Integer recipientSeq,
        String recipientNo,
        Integer resultCode,
        String resultMessage,
        String recipientGroupingKey
) {
}
