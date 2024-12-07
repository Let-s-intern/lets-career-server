package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.faq.helper.FaqHelper;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;
import org.letscareer.letscareer.domain.report.dto.res.GetReportDetailForAdminResponseDto;
import org.letscareer.letscareer.domain.report.helper.ReportHelper;
import org.letscareer.letscareer.domain.report.mapper.ReportMapper;
import org.letscareer.letscareer.domain.report.service.GetReportDetailForAdminService;
import org.letscareer.letscareer.domain.report.vo.ReportDetailForAdminVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.helper.UserHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class GetReportDetailForAdminServiceImpl implements GetReportDetailForAdminService {
    private final UserHelper userHelper;
    private final ReportHelper reportHelper;
    private final ReportMapper reportMapper;
    private final FaqHelper faqHelper;

    @Override
    public GetReportDetailForAdminResponseDto execute(User user, Long reportId) {
        userHelper.validateAdminUser(user);
        ReportDetailForAdminVo vo = reportHelper.findReportDetailForAdminVoOrThrow(reportId);
        List<FaqDetailVo> faqInfo = faqHelper.findReportFaqDetailVos(reportId);
        return reportMapper.toGetReportDetailForAdminResponseDto(vo, faqInfo);
    }
}
