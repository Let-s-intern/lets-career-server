package org.letscareer.letscareer.domain.live.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.admincalssification.helper.LiveAdminClassificationHelper;
import org.letscareer.letscareer.domain.admincalssification.request.CreateLiveAdminClassificationRequestDto;
import org.letscareer.letscareer.domain.admincalssification.vo.LiveAdminClassificationDetailVo;
import org.letscareer.letscareer.domain.application.dto.response.GetLiveApplicationsResponseDto;
import org.letscareer.letscareer.domain.application.helper.LiveApplicationHelper;
import org.letscareer.letscareer.domain.application.mapper.LiveApplicationMapper;
import org.letscareer.letscareer.domain.application.vo.AdminLiveApplicationVo;
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
import org.letscareer.letscareer.domain.live.dto.request.UpdateLiveRequestDto;
import org.letscareer.letscareer.domain.live.dto.response.*;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.live.helper.LiveHelper;
import org.letscareer.letscareer.domain.live.mapper.LiveMapper;
import org.letscareer.letscareer.domain.live.type.MentorContentsType;
import org.letscareer.letscareer.domain.live.vo.*;
import org.letscareer.letscareer.domain.price.dto.request.CreateLivePriceRequestDto;
import org.letscareer.letscareer.domain.price.helper.LivePriceHelper;
import org.letscareer.letscareer.domain.price.vo.LivePriceDetailVo;
import org.letscareer.letscareer.domain.program.dto.response.ZoomMeetingResponseDto;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.review.dto.response.GetOldReviewResponseDto;
import org.letscareer.letscareer.domain.review.helper.OldReviewHelper;
import org.letscareer.letscareer.domain.review.mapper.OldReviewMapper;
import org.letscareer.letscareer.domain.review.vo.old.OldReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.old.OldReviewVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.letscareer.letscareer.global.common.utils.zoom.ZoomUtils;
import org.letscareer.letscareer.global.error.exception.InvalidValueException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.letscareer.letscareer.domain.live.error.LiveErrorCode.MENTOR_PASSWORD_WRONG;

@RequiredArgsConstructor
@Transactional
@Service
public class LiveServiceImpl implements LiveService {
    private static final int MENTOR_PASSWORD_LENGTH = 4;
    private final LiveHelper liveHelper;
    private final LiveMapper liveMapper;
    private final LiveApplicationHelper liveApplicationHelper;
    private final LiveApplicationMapper liveApplicationMapper;
    private final LiveClassificationHelper liveClassificationHelper;
    private final LiveAdminClassificationHelper liveAdminClassificationHelper;
    private final LivePriceHelper livePriceHelper;
    private final OldReviewHelper oldReviewHelper;
    private final OldReviewMapper oldReviewMapper;
    private final FaqHelper faqHelper;
    private final FaqMapper faqMapper;
    private final ZoomUtils zoomUtils;

    @Override
    public GetLivesResponseDto getLiveList(List<ProgramClassification> typeList, List<ProgramStatusType> statusList, Pageable pageable) {
        Page<LiveProfileVo> liveProfileVos = liveHelper.findLiveProfileVos(typeList, statusList, pageable);
        return liveMapper.toGetLivesResponseDto(liveProfileVos);
    }

    @Override
    public GetLiveDetailResponseDto getLiveDetail(Long liveId) {
        LiveDetailVo liveInfo = liveHelper.findLiveDetailVoOrThrow(liveId);
        List<LiveClassificationVo> classificationInfo = liveClassificationHelper.findLiveClassificationVos(liveId);
        List<LiveAdminClassificationDetailVo> adminClassificationInfo = liveAdminClassificationHelper.findAdminClassificationDetailVos(liveId);
        LivePriceDetailVo priceInfo = livePriceHelper.findLivePriceDetailVos(liveId);
        List<FaqDetailVo> faqInfo = faqHelper.findLiveFaqDetailVos(liveId);
        return liveMapper.toLiveDetailResponseDto(liveInfo, classificationInfo, adminClassificationInfo, priceInfo, faqInfo);
    }

    @Override
    public GetLiveTitleResponseDto getLiveTitle(Long liveId) {
        LiveTitleVo titleVo = liveHelper.findLiveTitleVoOrThrow(liveId);
        return liveMapper.toGetLiveTitleResponseDto(titleVo);
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
    public GetLiveReviewsResponseDto getLiveReviews(Pageable pageable) {
        Page<OldReviewVo> reviewVos = oldReviewHelper.findLiveReviewVos(pageable);
        List<GetOldReviewResponseDto> reviewResDtoList = createGetReviewResponseDtoList(reviewVos.getContent());
        PageInfo pageInfo = PageInfo.of(reviewVos);
        return liveMapper.toGetLiveReviewsResponseDto(reviewResDtoList, pageInfo);
    }

    private List<GetOldReviewResponseDto> createGetReviewResponseDtoList(List<OldReviewVo> vos) {
        return vos.stream()
                .map(vo -> oldReviewMapper.toGetReviewResponseDto(vo, liveHelper.findLiveTitleVoOrThrow(vo.id()).title()))
                .collect(Collectors.toList());
    }

    @Override
    public GetLiveApplicationFormResponseDto getLiveApplicationForm(User user, Long liveId) {
        LiveApplicationFormVo LiveApplicationFormVo = liveHelper.findLiveApplicationFormVoOrThrow(liveId);
        LivePriceDetailVo livePriceDetailVo = livePriceHelper.findLivePriceDetailVos(liveId);
        Boolean applied = liveApplicationHelper.checkExistingLiveApplication(user, liveId);
        return liveMapper.toGetLiveApplicationFormResponseDto(user, applied, LiveApplicationFormVo, livePriceDetailVo);
    }

    @Override
    public GetLiveApplicationsResponseDto getApplications(Long liveId, Boolean isCanceled) {
        List<AdminLiveApplicationVo> applicationVos = liveApplicationHelper.findAdminLiveApplicationVos(liveId, isCanceled);
        return liveApplicationMapper.toGetLiveApplicationsResponseDto(applicationVos);
    }

    @Override
    public GetLiveAdminReviewsResponseDto getReviewsForAdmin(Long liveId, Pageable pageable) {
        Page<OldReviewAdminVo> reviewVos = oldReviewHelper.findLiveReviewAdminVos(liveId, pageable);
        return liveMapper.toGetLiveAdminReviewsResponseDto(reviewVos);
    }

    @Override
    public GetLiveExisingApplicationResponseDto getLiveExistingApplication(Long liveId, Long userId) {
        Boolean applied = liveApplicationHelper.existLiveApplicationByLiveIdAndUserId(liveId, userId);
        return liveApplicationMapper.toGetLiveExisingApplicationResponseDto(applied);
    }

    @Override
    public GetLiveMentorContentsResponse getMentorContents(Long liveId, String mentorPassword, MentorContentsType mentorContentsType) {
        validateMentorPassword(liveId, mentorPassword);
        LiveMentorVo liveMentorVo = liveHelper.findLiveMentorVoOrThrow(liveId);
        List<String> questionList = mentorContentsType.equals(MentorContentsType.PREV) ? liveApplicationHelper.findQuestionListByLiveId(liveId) : new ArrayList<>();
        List<String> motivateList = mentorContentsType.equals(MentorContentsType.PREV) ? liveApplicationHelper.findMotivateListByLiveId(liveId) : new ArrayList<>();
        List<String> reviewList = mentorContentsType.equals(MentorContentsType.REVIEW) ? oldReviewHelper.findLiveReviewContentByLiveId(liveId) : new ArrayList<>();
        return liveMapper.toGetLiveMentorContentsResponse(liveMentorVo, questionList, motivateList, reviewList);
    }

    @Override
    public GetLiveMentorPasswordResponseDto getMentorPassword(Long liveId) {
        String mentorPassword = liveHelper.findLiveMentorPasswordByIdOrThrow(liveId);
        GetLiveMentorPasswordResponseDto responseDto = GetLiveMentorPasswordResponseDto.of(mentorPassword);
        return responseDto;
    }

    @Override
    public void createLive(CreateLiveRequestDto requestDto) {
        ZoomMeetingResponseDto zoomMeetingInfo = zoomUtils.createZoomMeeting(requestDto.title(), requestDto.startDate());
        String mentorPassword = generateMentorPassword();
        Live live = liveHelper.createLiveAndSave(requestDto, mentorPassword, zoomMeetingInfo);
        createClassificationListAndSave(requestDto.programTypeInfo(), live);
        createAdminClassificationListAndSave(requestDto.adminProgramTypeInfo(), live);
        createPriceListAndSave(requestDto.priceInfo(), live);
        createFaqListAndSave(requestDto.faqInfo(), live);
    }

    @Override
    public void updateLive(Long liveId, UpdateLiveRequestDto requestDto) {
        Live live = liveHelper.findLiveByIdOrThrow(liveId);
        live.updateLive(requestDto);
        updateClassifications(live, requestDto.programTypeInfo());
        updateAdminClassifications(live, requestDto.adminProgramTypeInfo());
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

    private void createAdminClassificationListAndSave(List<CreateLiveAdminClassificationRequestDto> requestDtoList,
                                                      Live live) {
        requestDtoList.stream()
                .map(requestDto -> liveAdminClassificationHelper.createLiveAdminClassificationAndSave(requestDto, live))
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

    private void updateAdminClassifications(Live live, List<CreateLiveAdminClassificationRequestDto> adminProgramTypeInfo) {
        if (Objects.isNull(adminProgramTypeInfo)) return;
        liveAdminClassificationHelper.deleteLiveAdminClassificationsByLiveId(live.getId());
        live.setInitAdminClassificationList();
        createAdminClassificationListAndSave(adminProgramTypeInfo, live);
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

    private String generateMentorPassword() {
        SecureRandom secureRandom = new SecureRandom();
        int upperLimit = (int) Math.pow(10, MENTOR_PASSWORD_LENGTH);
        String mentorPassword = String.valueOf(secureRandom.nextInt(upperLimit));
        while(mentorPassword.length() < MENTOR_PASSWORD_LENGTH) {
            mentorPassword = "0" + mentorPassword;
        }
        return mentorPassword;
    }

    private void validateMentorPassword(Long liveId, String mentorPassword) {
        if(!liveHelper.validateMentorPassword(liveId, mentorPassword)) {
            throw new InvalidValueException(MENTOR_PASSWORD_WRONG);
        }
    }
}
