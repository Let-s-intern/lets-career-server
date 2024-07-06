package org.letscareer.letscareer.domain.attendance.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.attendance.type.AttendanceStatus;
import org.letscareer.letscareer.global.common.utils.e.AbstractEnumCodeAttributeConverter;

@Converter
public class AttendanceStatusConverter extends AbstractEnumCodeAttributeConverter<AttendanceStatus> {
    public AttendanceStatusConverter() {
        super(AttendanceStatus.class);
    }
}
