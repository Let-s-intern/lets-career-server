package org.letscareer.letscareer.domain.live.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.response.GetLiveApplicationsResponseDto;
import org.letscareer.letscareer.domain.application.helper.LiveApplicationHelper;
import org.letscareer.letscareer.domain.application.mapper.LiveApplicationMapper;
import org.letscareer.letscareer.domain.application.vo.AdminLiveApplicationVo;
import org.letscareer.letscareer.domain.live.dto.response.GetLiveApplicationFormResponseDto;
import org.letscareer.letscareer.domain.classification.dto.request.CreateLiveClassificationRequestDto;
import org.letscareer.letscareer.domain.classification.helper.LiveClassificationHelper;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.classification.vo.LiveClassificationVo;
import org.letscareer.letscareer.domain.faq.dto.request.CreateProgramFaqRequestDto;
import org.letscareer.letscareer.domain.faq.dto.response.GetFaqResponseDto;
import org.letscareer.letscareer.domain.faq.entity.Faq;
import org.letscareer.letscareer.domain.faq.helper.FaqHelper;
import org.letscareer.letscareer.domain.faq.mapper.FaqMapper;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;
import org.letscareer.letscareer.domain.live.dto.request.CreateLiveRequestDto;
import org.letscareer.letscareer.domain.live.dto.response.*;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.live.helper.LiveHelper;
import org.letscareer.letscareer.domain.live.mapper.LiveMapper;
import org.letscareer.letscareer.domain.live.vo.*;
import org.letscareer.letscareer.domain.price.dto.request.CreateLivePriceRequestDto;
import org.letscareer.letscareer.domain.price.helper.LivePriceHelper;
import org.letscareer.letscareer.domain.price.vo.LivePriceDetailVo;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.review.helper.ReviewHelper;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class LiveServiceImpl implements LiveService {
    private final LiveHelper liveHelper;
    private final LiveMapper liveMapper;
    private final LiveApplicationHelper liveApplicationHelper;
    private final LiveApplicationMapper liveApplicationMapper;
    private final LiveClassificationHelper liveClassificationHelper;
    private final LivePriceHelper livePriceHelper;
    private final ReviewHelper reviewHelper;
    private final FaqHelper faqHelper;
    private final FaqMapper faqMapper;

    @Override
    public GetLivesResponseDto getLiveList(List<ProgramClassification> typeList, List<ProgramStatusType> statusList, Pageable pageable) {
        Page<LiveProfileVo> liveProfileVos = liveHelper.findLiveProfileVos(typeList, statusList, pageable);
        return liveMapper.toGetLivesResponseDto(liveProfileVos);
    }

    @Override
    public GetLiveDetailResponseDto getLiveDetail(Long liveId) {
        LiveDetailVo liveInfo = liveHelper.findLiveDetailVoOrThrow(liveId);
        List<LiveClassificationVo> classificationInfo = liveClassificationHelper.findLiveClassificationVos(liveId);
        LivePriceDetailVo priceInfo = livePriceHelper.findLivePriceDetailVos(liveId);
        List<FaqDetailVo> faqInfo = faqHelper.findLiveFaqDetailVos(liveId);
        return liveMapper.toLiveDetailResponseDto(liveInfo, classificationInfo, priceInfo, faqInfo);
    }

    @Override
    public GetLiveThumbnailResponseDto getLiveThumbnail(Long liveId) {
        LiveThumbnailVo thumbnailVo = liveHelper.findLiveThumbnailVoOrThrow(liveId);
        return liveMapper.toGetLiveThumbnailResponseDto(thumbnailVo);
    }

    @Override
    public GetLiveContentResponseDto getLiveDetailContent(Long liveId) {
        LiveContentVo contentVo = liveHelper.findLiveContentVoOrThrow(liveId);
        return liveMapper.toGetLiveContentResponseDto(contentVo);
    }

    @Override
    public GetFaqResponseDto getLiveFaqs(Long liveId) {
        List<FaqDetailVo> faqList = faqHelper.findLiveFaqDetailVos(liveId);
        return faqMapper.toGetFaqResponseDto(faqList);
    }

    @Override
    public GetLiveApplicationFormResponseDto getLiveApplicationForm(User user, Long liveId) {
        LiveApplicationFormVo LiveApplicationFormVo = liveHelper.findLiveApplicationFormVoOrThrow(liveId);
        LivePriceDetailVo livePriceDetailVo = livePriceHelper.findLivePriceDetailVos(liveId);
        return liveMapper.toGetLiveApplicationFormResponseDto(user, LiveApplicationFormVo, livePriceDetailVo);
    }

    @Override
    public GetLiveApplicationsResponseDto getApplications(Long liveId, Boolean isConfirmed) {
        List<AdminLiveApplicationVo> applicationVos = liveApplicationHelper.findAdminLiveApplicationVos(liveId, isConfirmed);
        return liveApplicationMapper.toGetLiveApplicationsResponseDto(applicationVos);
    }

    @Override
    public GetLiveReviewsResponseDto getReviews(Long liveId, Pageable pageable) {
        Page<ReviewVo> reviewVos = reviewHelper.findLiveReviewVos(liveId, pageable);
        return liveMapper.toGetLiveReviewsResponseDto(reviewVos);
    }

    @Override
    public void createLive(CreateLiveRequestDto requestDto) {
        Live live = liveHelper.createLiveAndSave(requestDto);
        createClassificationListAndSave(requestDto.programTypeInfo(), live);
        createPriceListAndSave(requestDto.priceInfo(), live);
        createFaqListAndSave(requestDto.faqInfo(), live);
    }

    @Override
    public void updateLive(Long liveId, CreateLiveRequestDto requestDto) {
        Live live = liveHelper.findLiveByIdOrThrow(liveId);
        live.updateLive(requestDto);
        updateClassifications(live, requestDto.programTypeInfo());
        updatePrice(live, requestDto.priceInfo());
        updateFaqs(live, requestDto.faqInfo());
    }

    @Override
    public void deleteLive(Long liveId) {
        liveHelper.deleteLiveById(liveId);
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

    private void createFaqListAndSave(List<CreateProgramFaqRequestDto> requestDtoList,
                                      Live live) {
        List<Faq> faqs = getFaqsById(requestDtoList);
        faqs.stream().forEach(faq -> {
            faqHelper.createFaqLiveAndSave(faq, live);
        });
    }

    private void updateClassifications(Live live, List<CreateLiveClassificationRequestDto> programTypeInfo) {
        if (Objects.isNull(programTypeInfo)) return;
        liveClassificationHelper.deleteLiveClassificationsByLiveId(live.getId());
        live.setInitClassificationList();
        createClassificationListAndSave(programTypeInfo, live);
    }

    private void updatePrice(Live live, CreateLivePriceRequestDto priceInfo) {
        if (Objects.isNull(priceInfo)) return;
        livePriceHelper.deleteLivePriceByLiveId(live.getId());
        live.setInitPriceList();
        createPriceListAndSave(priceInfo, live);
    }

    private void updateFaqs(Live live, List<CreateProgramFaqRequestDto> faqInfo) {
        if (Objects.isNull(faqInfo)) return;
        faqHelper.deleteLiveFaqsByLiveId(live.getId());
        live.setInitFaqList();
        createFaqListAndSave(faqInfo, live);
    }

    private List<Faq> getFaqsById(List<CreateProgramFaqRequestDto> requestDtoList) {
        return requestDtoList.stream()
                .map(request -> faqHelper.findFaqByIdAndThrow(request.faqId()))
                .collect(Collectors.toList());
    }
}
