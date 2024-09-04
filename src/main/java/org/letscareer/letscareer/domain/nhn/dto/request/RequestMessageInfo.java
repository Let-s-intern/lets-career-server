package org.letscareer.letscareer.domain.nhn.dto.request;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record RequestMessageInfo<T>(
        T requestParameter,
        String templateCode
) {
    public static <T> RequestMessageInfo<?> of(T requestParameter, String templateCode) {
        return RequestMessageInfo.builder()
                .requestParameter(requestParameter)
                .templateCode(templateCode)
                .build();
    }
}
