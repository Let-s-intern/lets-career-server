package org.letscareer.letscareer.domain.nhn.dto.request.live;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.live.entity.Live;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record LiveClassRemindParameter(
        String userName,
        String programTitle,
        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm", timezone = "Asia/Seoul")
        LocalDateTime programStartDate,
        String zoomLink
) {
        public static LiveClassRemindParameter of(String userName,
                                                  Live live) {
                return LiveClassRemindParameter.builder()
                        .userName(userName)
                        .programTitle(live.getTitle())
                        .programStartDate(live.getStartDate())
                        .zoomLink(live.getZoomLink())
                        .build();
        }
}
