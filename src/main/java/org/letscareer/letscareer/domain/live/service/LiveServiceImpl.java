package org.letscareer.letscareer.domain.live.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.classification.dto.request.CreateLiveClassificationRequestDto;
import org.letscareer.letscareer.domain.classification.helper.LiveClassificationHelper;
import org.letscareer.letscareer.domain.faq.dto.request.CreateFaqRequestDto;
import org.letscareer.letscareer.domain.faq.helper.FaqHelper;
import org.letscareer.letscareer.domain.live.dto.request.CreateLiveRequestDto;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.live.helper.LiveHelper;
import org.letscareer.letscareer.domain.price.dto.request.CreateLivePriceRequestDto;
import org.letscareer.letscareer.domain.price.helper.LivePriceHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LiveServiceImpl implements LiveService {
    private final LiveHelper liveHelper;
    private final LiveClassificationHelper liveClassificationHelper;
    private final LivePriceHelper livePriceHelper;
    private final FaqHelper faqHelper;

    @Override
    public void createLiveService(CreateLiveRequestDto requestDto) {
        Live live = liveHelper.createLiveAndSave(requestDto);
        createClassificationListAndSave(requestDto.programTypeInfo(), live);
        createPriceListAndSave(requestDto.priceInfo(), live);
        createFaqListAndSave(requestDto.faqInfo(), live);
    }

    private void createClassificationListAndSave(List<CreateLiveClassificationRequestDto> requestDtoList,
                                                 Live live) {
        requestDtoList.stream()
                .map(requestDto -> liveClassificationHelper.createLiveClassificationAndSave(requestDto, live))
                .collect(Collectors.toList());
    }

    private void createPriceListAndSave(CreateLivePriceRequestDto requestDto,
                                        Live live) {
        livePriceHelper.createLivePriceAndSave(requestDto, live);
    }

    private void createFaqListAndSave(List<CreateFaqRequestDto> requestDtoList,
                                      Live live) {
        requestDtoList.stream()
                .map(requestDto -> faqHelper.createFaqLiveAndSave(requestDto, live))
                .collect(Collectors.toList());
    }
}
