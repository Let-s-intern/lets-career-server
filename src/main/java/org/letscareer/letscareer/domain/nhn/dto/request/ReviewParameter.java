package org.letscareer.letscareer.domain.nhn.dto.request;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record ReviewParameter(
        String userName
) {
    public static ReviewParameter of(String userName) {
        return ReviewParameter.builder()
                .userName(userName)
                .build();
    }
}
