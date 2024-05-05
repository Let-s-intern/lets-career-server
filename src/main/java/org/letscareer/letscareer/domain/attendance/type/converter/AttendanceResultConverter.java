package org.letscareer.letscareer.domain.attendance.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.attendance.type.AttendanceResult;
import org.letscareer.letscareer.global.common.utils.AbstractEnumCodeAttributeConverter;

@Converter
public class AttendanceResultConverter extends AbstractEnumCodeAttributeConverter<AttendanceResult> {
    public AttendanceResultConverter() {
        super(AttendanceResult.class);
    }
}
