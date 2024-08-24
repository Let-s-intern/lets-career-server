package org.letscareer.letscareer.domain.report.type;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.global.common.utils.enm.AbstractEnumCodeAttributeConverter;

@Converter
public class ReportPriceTypeConverter extends AbstractEnumCodeAttributeConverter<ReportPriceType> {

    public ReportPriceTypeConverter() {
        super(ReportPriceType.class);
    }
}

