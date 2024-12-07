package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.faq.dto.response.GetFaqResponseDto;
import org.letscareer.letscareer.domain.faq.helper.FaqHelper;
import org.letscareer.letscareer.domain.faq.mapper.FaqMapper;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;
import org.letscareer.letscareer.domain.report.service.GetReportFaqsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class GetReportFaqsServiceImpl implements GetReportFaqsService {
    private final FaqHelper faqHelper;
    private final FaqMapper faqMapper;

    @Override
    public GetFaqResponseDto execute(Long reportId) {
        List<FaqDetailVo> faqList = faqHelper.findReportFaqDetailVos(reportId);
        return faqMapper.toGetFaqResponseDto(faqList);
    }
}
