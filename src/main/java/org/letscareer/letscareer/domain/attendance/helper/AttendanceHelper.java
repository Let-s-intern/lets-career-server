package org.letscareer.letscareer.domain.attendance.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.attendance.repository.AttendanceRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class AttendanceHelper {
    private final AttendanceRepository attendanceRepository;

}
