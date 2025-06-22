package org.letscareer.letscareer.domain.attendance.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.attendance.type.AttendanceFeedbackStatus;
import org.letscareer.letscareer.global.common.utils.enm.AbstractEnumCodeAttributeConverter;

@Converter
public class AttendanceFeedbackStatusConverter extends AbstractEnumCodeAttributeConverter<AttendanceFeedbackStatus> {
    public AttendanceFeedbackStatusConverter() {
        super(AttendanceFeedbackStatus.class);
    }
}
