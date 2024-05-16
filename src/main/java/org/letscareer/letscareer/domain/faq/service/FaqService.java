package org.letscareer.letscareer.domain.faq.service;

import org.letscareer.letscareer.domain.faq.dto.request.CreateFaqRequestDto;
import org.letscareer.letscareer.domain.faq.dto.response.GetFaqResponseDto;
import org.letscareer.letscareer.domain.faq.type.FaqProgramType;
import org.springframework.stereotype.Service;

@Service
public interface FaqService {
    GetFaqResponseDto getFaqs(FaqProgramType type);

    void creatFaq(CreateFaqRequestDto createProgramFaqRequestDto);

    void updateFaq(Long faqId, CreateFaqRequestDto createProgramFaqRequestDto);

    void deleteFaq(Long faqId);
}
