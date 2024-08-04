package org.letscareer.letscareer.domain.nhn.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.user.entity.User;

@Builder(access = AccessLevel.PRIVATE)
public record RecipientInfo<T>(
        String recipientNo,
        T templateParameter
) {
    public static <T> RecipientInfo<?> of(String recipientNo,
                                          T templateParameter) {
        return RecipientInfo.builder()
                .recipientNo(recipientNo)
                .templateParameter(templateParameter)
                .build();
    }

    public static <T> RecipientInfo<?> ofUser(User user,
                                              T templateParameter) {
        return RecipientInfo.builder()
                .recipientNo(user.getName())
                .templateParameter(templateParameter)
                .build();
    }
}
