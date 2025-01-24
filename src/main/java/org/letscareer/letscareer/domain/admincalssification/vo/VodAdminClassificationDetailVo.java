package org.letscareer.letscareer.domain.admincalssification.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.admincalssification.type.ProgramAdminClassification;

@Builder
public record VodAdminClassificationDetailVo(
        ProgramAdminClassification programAdminClassification
) {
}
