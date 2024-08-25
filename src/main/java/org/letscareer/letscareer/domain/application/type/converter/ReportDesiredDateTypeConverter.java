package org.letscareer.letscareer.domain.application.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.application.type.ReportDesiredDateType;
import org.letscareer.letscareer.global.common.utils.enm.AbstractEnumCodeAttributeConverter;

@Converter
public class ReportDesiredDateTypeConverter extends AbstractEnumCodeAttributeConverter<ReportDesiredDateType> {
    public ReportDesiredDateTypeConverter() {
        super(ReportDesiredDateType.class);
    }
}
