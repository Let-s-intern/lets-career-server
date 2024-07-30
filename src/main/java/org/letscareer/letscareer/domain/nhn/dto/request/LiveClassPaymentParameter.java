package org.letscareer.letscareer.domain.nhn.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.live.entity.Live;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record LiveClassPaymentParameter(
        String userName,
        String programTitle,
        @JsonFormat(pattern = "yyyy년 MM월 dd일", timezone = "Asia/Seoul")
        LocalDateTime programStartDate,
        String zoomLink
) {
    public static LiveClassPaymentParameter of(String userName,
                                               Live live) {
        return LiveClassPaymentParameter.builder()
                .userName(userName)
                .programTitle(live.getTitle())
                .programStartDate(live.getStartDate())
                .zoomLink(live.getZoomLink().substring(8))
                .build();
    }
}
