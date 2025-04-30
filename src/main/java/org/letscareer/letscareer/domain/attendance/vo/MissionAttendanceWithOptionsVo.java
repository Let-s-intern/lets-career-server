package org.letscareer.letscareer.domain.attendance.vo;

import java.util.List;

public record MissionAttendanceWithOptionsVo(
        MissionAttendanceVo attendance,
        List<String> optionCodes
) {
}
