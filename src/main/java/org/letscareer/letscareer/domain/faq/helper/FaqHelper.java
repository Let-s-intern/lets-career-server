package org.letscareer.letscareer.domain.faq.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.faq.dto.request.CreateFaqRequestDto;
import org.letscareer.letscareer.domain.faq.entity.Faq;
import org.letscareer.letscareer.domain.faq.entity.FaqChallenge;
import org.letscareer.letscareer.domain.faq.entity.FaqLive;
import org.letscareer.letscareer.domain.faq.repository.FaqChallengeRepository;
import org.letscareer.letscareer.domain.faq.repository.FaqLiveRepository;
import org.letscareer.letscareer.domain.faq.repository.FaqRepository;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class FaqHelper {
    private final FaqRepository faqRepository;
    private final FaqChallengeRepository faqChallengeRepository;
    private final FaqLiveRepository faqLiveRepository;

    public FaqChallenge createFaqChallengeAndSave(CreateFaqRequestDto requestDto,
                                                  Challenge challenge) {
        Faq faq = createFaqAndSave(requestDto);
        FaqChallenge faqChallenge = FaqChallenge.createFaqChallenge(faq, challenge);
        return faqChallengeRepository.save(faqChallenge);
    }

    public FaqLive createFaqLiveAndSave(CreateFaqRequestDto requestDto,
                                        Live live) {
        Faq faq = createFaqAndSave(requestDto);
        FaqLive faqLive = FaqLive.createFaqLive(faq, live);
        return faqLiveRepository.save(faqLive);
    }

    public Faq createFaqAndSave(CreateFaqRequestDto requestDto) {
        Faq faq = Faq.createFaq(requestDto);
        return faqRepository.save(faq);
    }
}
