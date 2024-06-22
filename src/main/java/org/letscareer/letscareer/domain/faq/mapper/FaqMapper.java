package org.letscareer.letscareer.domain.faq.mapper;

import org.letscareer.letscareer.domain.faq.dto.response.GetFaqResponseDto;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FaqMapper {
    public GetFaqResponseDto toGetFaqResponseDto(List<FaqDetailVo> vos) {
        return GetFaqResponseDto.of(vos);
    }
}
