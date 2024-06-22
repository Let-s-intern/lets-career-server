package org.letscareer.letscareer.domain.application.repository;

import org.letscareer.letscareer.domain.application.type.ApplicationStatus;
import org.letscareer.letscareer.domain.application.vo.MyApplicationVo;
import org.letscareer.letscareer.domain.program.vo.ProgramSimpleVo;

import java.util.List;

public interface ApplicationQueryRepository {
    List<MyApplicationVo> findMyApplications(Long userId, ApplicationStatus status);

    ProgramSimpleVo findVWApplicationProgramIdById(Long applicationId);
}
