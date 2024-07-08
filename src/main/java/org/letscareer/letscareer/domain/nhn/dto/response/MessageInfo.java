package org.letscareer.letscareer.domain.nhn.dto.response;

import java.util.List;

public record MessageInfo(
        String requestId,
        String senderGroupingKey,
        List<ResultInfo> sendResults
) {
}
