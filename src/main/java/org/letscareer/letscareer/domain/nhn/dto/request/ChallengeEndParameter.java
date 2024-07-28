package org.letscareer.letscareer.domain.nhn.dto.request;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record ChallengeEndParameter(
        String userName,
        String programTitle,
        Long programId
) {
    public static ChallengeEndParameter of(String userName,
                                           String programTitle,
                                           Long programId) {
        return ChallengeEndParameter.builder()
                .userName(userName)
                .programTitle(programTitle)
                .programId(programId)
                .build();
    }
}
