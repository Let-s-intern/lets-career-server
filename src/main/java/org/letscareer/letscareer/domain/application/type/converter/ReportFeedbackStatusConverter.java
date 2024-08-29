package org.letscareer.letscareer.domain.application.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.application.type.ReportFeedbackStatus;
import org.letscareer.letscareer.global.common.utils.enm.AbstractEnumCodeAttributeConverter;

@Converter
public class ReportFeedbackStatusConverter extends AbstractEnumCodeAttributeConverter<ReportFeedbackStatus> {
    public ReportFeedbackStatusConverter() {
        super(ReportFeedbackStatus.class);
    }
}

