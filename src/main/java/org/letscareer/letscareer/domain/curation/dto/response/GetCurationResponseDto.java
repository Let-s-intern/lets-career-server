package org.letscareer.letscareer.domain.curation.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.curation.vo.CurationItemVo;
import org.letscareer.letscareer.domain.curation.vo.CurationVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetCurationResponseDto(
        CurationVo curationInfo,
        List<CurationItemVo> curationItemList
) {
    public static GetCurationResponseDto of(CurationVo curationInfo,
                                            List<CurationItemVo> curationItemList) {
        return GetCurationResponseDto.builder()
                .curationInfo(curationInfo)
                .curationItemList(curationItemList)
                .build();
    }
}
