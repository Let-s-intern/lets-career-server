package org.letscareer.letscareer.domain.report.type;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.global.common.utils.enm.AbstractEnumCodeAttributeConverter;

@Converter
public class ReportTypeConverter extends AbstractEnumCodeAttributeConverter<ReportType> {

    public ReportTypeConverter() {
        super(ReportType.class);
    }
}
