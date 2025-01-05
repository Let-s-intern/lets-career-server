package org.letscareer.letscareer.domain.application.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.application.type.ReportApplicationSubmitType;
import org.letscareer.letscareer.global.common.utils.enm.AbstractEnumCodeAttributeConverter;

@Converter
public class ReportApplicationSubmitTypeConverter extends AbstractEnumCodeAttributeConverter<ReportApplicationSubmitType> {
    public ReportApplicationSubmitTypeConverter() {
        super(ReportApplicationSubmitType.class);
    }
}
