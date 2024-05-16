package org.letscareer.letscareer.domain.faq.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.faq.dto.request.CreateFaqRequestDto;
import org.letscareer.letscareer.domain.faq.dto.request.CreateProgramFaqRequestDto;
import org.letscareer.letscareer.domain.faq.dto.response.GetFaqResponseDto;
import org.letscareer.letscareer.domain.faq.entity.Faq;
import org.letscareer.letscareer.domain.faq.helper.FaqHelper;
import org.letscareer.letscareer.domain.faq.mapper.FaqMapper;
import org.letscareer.letscareer.domain.faq.type.FaqProgramType;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class FaqServiceImpl implements FaqService {
    private final FaqHelper faqHelper;
    private final FaqMapper faqMapper;

    @Override
    public GetFaqResponseDto getFaqs(FaqProgramType type) {
        List<FaqDetailVo> faqDetailVos = faqHelper.findFaqDetailVosByType(type);
        return faqMapper.toGetFaqResponseDto(faqDetailVos);
    }

    @Override
    public void creatFaq(CreateFaqRequestDto createFaqRequestDto) {
        faqHelper.createFaqAndSave(createFaqRequestDto);
    }

    @Override
    public void updateFaq(Long faqId, CreateFaqRequestDto createFaqRequestDto) {
        Faq faq = faqHelper.findFaqByIdAndThrow(faqId);
        faq.updateFaq(createFaqRequestDto);
    }

    @Override
    public void deleteFaq(Long faqId) {
        faqHelper.deleteFaqById(faqId);
    }

}
