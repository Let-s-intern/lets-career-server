package org.letscareer.letscareer.domain.application.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.application.type.ReportApplicationStatus;
import org.letscareer.letscareer.global.common.utils.enm.AbstractEnumCodeAttributeConverter;

@Converter
public class ReportApplicationStatusConverter extends AbstractEnumCodeAttributeConverter<ReportApplicationStatus> {
    public ReportApplicationStatusConverter() {
        super(ReportApplicationStatus.class);
    }
}
