package org.letscareer.letscareer.domain.challenge.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.dto.request.CreateChallengeRequestDto;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challenge.helper.ChallengeHelper;
import org.letscareer.letscareer.domain.classification.dto.request.CreateChallengeClassificationRequestDto;
import org.letscareer.letscareer.domain.classification.helper.ChallengeClassificationHelper;
import org.letscareer.letscareer.domain.faq.dto.request.CreateFaqRequestDto;
import org.letscareer.letscareer.domain.faq.helper.FaqHelper;
import org.letscareer.letscareer.domain.price.dto.request.CreateChallengePriceRequestDto;
import org.letscareer.letscareer.domain.price.helper.ChallengePriceHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChallengeServiceImpl implements ChallengeService {
    private final ChallengeHelper challengeHelper;
    private final ChallengeClassificationHelper challengeClassificationHelper;
    private final ChallengePriceHelper challengePriceHelper;
    private final FaqHelper faqHelper;

    @Override
    public void createChallenge(CreateChallengeRequestDto createChallengeRequestDto) {
        Challenge challenge = challengeHelper.createChallengeAndSave(createChallengeRequestDto);
        createClassificationListAndSave(createChallengeRequestDto.programTypeInfo(), challenge);
        createPriceListAndSave(createChallengeRequestDto.priceInfo(), challenge);
        createFaqListAndSave(createChallengeRequestDto.faqInfo(), challenge);
    }

    private void createClassificationListAndSave(List<CreateChallengeClassificationRequestDto> requestDtoList,
                                          Challenge challenge) {
        requestDtoList.stream()
                .map(requestDto -> challengeClassificationHelper.createChallengeClassificationAndSave(requestDto, challenge))
                .collect(Collectors.toList());
    }

    private void createPriceListAndSave(List<CreateChallengePriceRequestDto> requestDtoList,
                                 Challenge challenge) {
        requestDtoList.stream()
                .map(requestDto -> challengePriceHelper.createChallengePriceAndSave(requestDto, challenge))
                .collect(Collectors.toList());
    }

    private void createFaqListAndSave(List<CreateFaqRequestDto> requestDtoList,
                               Challenge challenge) {
        requestDtoList.stream()
                .map(requestDto -> faqHelper.createFaqChallengeAndSave(requestDto, challenge))
                .collect(Collectors.toList());
    }
}
