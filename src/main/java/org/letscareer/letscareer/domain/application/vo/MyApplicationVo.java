package org.letscareer.letscareer.domain.application.vo;

import lombok.Builder;

@Builder
public record MyApplicationVo(
        Long id,
        String programTitle,
        String programStartDate,
        String programEndDate
) {
}
