package org.letscareer.letscareer.domain.application.repository;

import org.letscareer.letscareer.domain.application.type.ApplicationStatus;
import org.letscareer.letscareer.domain.application.vo.MyApplicationVo;
import org.letscareer.letscareer.domain.payment.vo.PaymentProgramVo;
import org.letscareer.letscareer.domain.program.vo.ProgramSimpleVo;

import java.util.List;

public interface ApplicationQueryRepository {
    List<MyApplicationVo> findMyApplications(Long userId, ApplicationStatus status);

    List<PaymentProgramVo> findPaymentProgramVos(Long userId);

    ProgramSimpleVo findVWApplicationProgramIdById(Long applicationId);
}
