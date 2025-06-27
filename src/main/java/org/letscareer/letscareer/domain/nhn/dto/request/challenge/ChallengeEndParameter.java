package org.letscareer.letscareer.domain.nhn.dto.request.challenge;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record ChallengeEndParameter(
        String userName,
        String programTitle,
        Long programId,
        Long applicationId
) {
    public static ChallengeEndParameter of(String userName,
                                           String programTitle,
                                           Long programId,
                                           Long applicationId) {
        return ChallengeEndParameter.builder()
                .userName(userName)
                .programTitle(programTitle)
                .programId(programId)
                .applicationId(applicationId)
                .build();
    }
}
