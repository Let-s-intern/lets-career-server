package org.letscareer.letscareer.domain.faq.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetFaqResponseDto(
        List<FaqDetailVo> faqList
) {
    public static GetFaqResponseDto of(List<FaqDetailVo> faqList) {
        return GetFaqResponseDto.builder()
                .faqList(faqList)
                .build();
    }
}
