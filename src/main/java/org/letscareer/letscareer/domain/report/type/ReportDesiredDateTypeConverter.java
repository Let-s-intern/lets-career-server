package org.letscareer.letscareer.domain.report.type;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.global.common.utils.enm.AbstractEnumCodeAttributeConverter;

@Converter
public class ReportDesiredDateTypeConverter extends AbstractEnumCodeAttributeConverter<ReportDesiredDateType> {
    public ReportDesiredDateTypeConverter() {
        super(ReportDesiredDateType.class);
    }
}
