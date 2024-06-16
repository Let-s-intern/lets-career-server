package org.letscareer.letscareer.domain.challenge.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.response.GetChallengeApplicationsResponseDto;
import org.letscareer.letscareer.domain.application.helper.ChallengeApplicationHelper;
import org.letscareer.letscareer.domain.application.mapper.ChallengeApplicationMapper;
import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationVo;
import org.letscareer.letscareer.domain.application.vo.UserChallengeApplicationVo;
import org.letscareer.letscareer.domain.attendance.helper.AttendanceHelper;
import org.letscareer.letscareer.domain.attendance.mapper.AttendanceMapper;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceDailyMissionVo;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceScoreVo;
import org.letscareer.letscareer.domain.attendance.vo.MissionAttendanceVo;
import org.letscareer.letscareer.domain.challenge.dto.request.CreateChallengeRequestDto;
import org.letscareer.letscareer.domain.challenge.dto.request.UpdateChallengeApplicationPaybackRequestDto;
import org.letscareer.letscareer.domain.challenge.dto.request.UpdateChallengeRequestDto;
import org.letscareer.letscareer.domain.challenge.dto.response.*;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challenge.helper.ChallengeHelper;
import org.letscareer.letscareer.domain.challenge.mapper.ChallengeMapper;
import org.letscareer.letscareer.domain.challenge.vo.*;
import org.letscareer.letscareer.domain.challengeguide.helper.ChallengeGuideHelper;
import org.letscareer.letscareer.domain.challengeguide.vo.ChallengeGuideVo;
import org.letscareer.letscareer.domain.challlengenotice.helper.ChallengeNoticeHelper;
import org.letscareer.letscareer.domain.challlengenotice.vo.ChallengeNoticeVo;
import org.letscareer.letscareer.domain.classification.dto.request.CreateChallengeClassificationRequestDto;
import org.letscareer.letscareer.domain.classification.helper.ChallengeClassificationHelper;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.classification.vo.ChallengeClassificationDetailVo;
import org.letscareer.letscareer.domain.faq.dto.request.CreateProgramFaqRequestDto;
import org.letscareer.letscareer.domain.faq.dto.response.GetFaqResponseDto;
import org.letscareer.letscareer.domain.faq.entity.Faq;
import org.letscareer.letscareer.domain.faq.helper.FaqHelper;
import org.letscareer.letscareer.domain.faq.mapper.FaqMapper;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;
import org.letscareer.letscareer.domain.mission.dto.response.MissionApplicationScoreResponseDto;
import org.letscareer.letscareer.domain.mission.helper.MissionHelper;
import org.letscareer.letscareer.domain.mission.mapper.MissionMapper;
import org.letscareer.letscareer.domain.mission.vo.DailyMissionVo;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.helper.PaymentHelper;
import org.letscareer.letscareer.domain.price.dto.request.CreateChallengePriceRequestDto;
import org.letscareer.letscareer.domain.price.helper.ChallengePriceHelper;
import org.letscareer.letscareer.domain.price.vo.ChallengePriceDetailVo;
import org.letscareer.letscareer.domain.program.dto.response.ZoomMeetingResponseDto;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.review.helper.ReviewHelper;
import org.letscareer.letscareer.domain.review.vo.ReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.letscareer.letscareer.domain.score.entity.AttendanceScore;
import org.letscareer.letscareer.domain.score.helper.AttendanceScoreHelper;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.utils.ZoomUtils;
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
public class ChallengeServiceImpl implements ChallengeService {
    private final ChallengeHelper challengeHelper;
    private final ChallengeMapper challengeMapper;
    private final ChallengeClassificationHelper challengeClassificationHelper;
    private final ChallengeApplicationHelper challengeApplicationHelper;
    private final ChallengeApplicationMapper challengeApplicationMapper;
    private final ChallengePriceHelper challengePriceHelper;
    private final MissionHelper missionHelper;
    private final MissionMapper missionMapper;
    private final ChallengeGuideHelper challengeGuideHelper;
    private final ChallengeNoticeHelper challengeNoticeHelper;
    private final AttendanceScoreHelper attendanceScoreHelper;
    private final AttendanceHelper attendanceHelper;
    private final AttendanceMapper attendanceMapper;
    private final PaymentHelper paymentHelper;
    private final ReviewHelper reviewHelper;
    private final FaqHelper faqHelper;
    private final FaqMapper faqMapper;
    private final ZoomUtils zoomUtils;

    @Override
    public GetChallengeResponseDto getChallengeList(List<ProgramClassification> typeList, List<ProgramStatusType> statusList, Pageable pageable) {
        Page<ChallengeProfileVo> challengeProfileVos = challengeHelper.findChallengeProfiles(typeList, statusList, pageable);
        return challengeMapper.toGetChallengesResponseDto(challengeProfileVos);
    }

    @Override
    public GetChallengeDetailResponseDto getChallengeDetail(Long challengeId) {
        ChallengeDetailVo challengeDetailVo = challengeHelper.findChallengeDetailByIdOrThrow(challengeId);
        List<ChallengeClassificationDetailVo> classificationInfo = challengeClassificationHelper.findClassificationDetailVos(challengeId);
        List<ChallengePriceDetailVo> priceInfo = challengePriceHelper.findChallengePriceDetailVos(challengeId);
        List<FaqDetailVo> faqInfo = faqHelper.findChallengeFaqDetailVos(challengeId);
        return challengeMapper.toChallengeDetailResponseDto(challengeDetailVo, classificationInfo, priceInfo, faqInfo);
    }

    @Override
    public GetChallengeTitleResponseDto getChallengeTitle(Long challengeId) {
        ChallengeTitleVo titleVo = challengeHelper.findChallengeTitleVoOrThrow(challengeId);
        return challengeMapper.toGetChallengeTitleResponseDto(titleVo);
    }

    @Override
    public GetChallengeApplicationsResponseDto getApplications(Long challengeId, Boolean isConfirmed) {
        List<AdminChallengeApplicationVo> applicationVos = challengeApplicationHelper.findAdminChallengeApplicationVos(challengeId, isConfirmed);
        return challengeApplicationMapper.toGetChallengeApplicationsResponseDto(applicationVos);
    }

    @Override
    public GetChallengeApplicationsPaybackResponseDto getApplicationsScore(Long challengeId, Pageable pageable) {
        Page<UserChallengeApplicationVo> challengeApplicationVos = challengeApplicationHelper.findUserChallengeApplicationVo(challengeId, pageable);
        List<MissionApplicationScoreResponseDto> missionApplications = createMissionApplications(challengeApplicationVos.getContent(), challengeId);
        return challengeApplicationMapper.toGetChallengeApplicationsScoreResponseDto(missionApplications, challengeApplicationVos);
    }

    @Override
    public GetChallengeApplicationFormResponseDto getChallengeApplicationForm(User user, Long challengeId) {
        ChallengeApplicationFormVo applicationFormVo = challengeHelper.findChallengeApplicationFormVoOrThrow(challengeId);
        List<ChallengePriceDetailVo> challengePriceDetailVos = challengePriceHelper.findChallengePriceDetailVos(challengeId);
        Boolean applied = challengeApplicationHelper.checkExistingChallengeApplication(user, challengeId);
        return challengeMapper.toGetChallengeApplicationFormResponseDto(user, applied, applicationFormVo, challengePriceDetailVos);
    }

    @Override
    public GetChallengeThumbnailResponseDto getChallengeThumbnail(Long challengeId) {
        ChallengeThumbnailVo thumbnailVo = challengeHelper.findChallengeThumbnailOrThrow(challengeId);
        return challengeMapper.toChallengeThumbnailVo(thumbnailVo);
    }

    @Override
    public GetChallengeContentResponseDto getChallengeDetailContent(Long challengeId) {
        ChallengeContentVo contentVo = challengeHelper.findChallengeContentOrThrow(challengeId);
        return challengeMapper.toGetChallengeContentResponseDto(contentVo);
    }

    @Override
    public GetFaqResponseDto getChallengeFaqs(Long challengeId) {
        List<FaqDetailVo> faqDetailVos = faqHelper.findChallengeFaqDetailVos(challengeId);
        return faqMapper.toGetFaqResponseDto(faqDetailVos);
    }

    @Override
    public GetChallengeMissionAttendancesResponseDto getMissionAttendances(Long challengeId, Long missionId) {
        List<MissionAttendanceVo> attendanceVos = attendanceHelper.findMissionAttendanceVo(challengeId, missionId);
        return attendanceMapper.toGetChallengeMissionAttendancesResponseDto(attendanceVos);
    }

    @Override
    public GetChallengeAdminReviewResponseDto getReviewsForAdmin(Long challengeId, Pageable pageable) {
        Page<ReviewAdminVo> challengeReviewVos = reviewHelper.findChallengeReviewAdminVos(challengeId, pageable);
        return challengeMapper.toGetChallengeAdminReviewResponseDto(challengeReviewVos);
    }

    @Override
    public GetChallengeReviewResponseDto getReviews(Pageable pageable) {
        Page<ReviewVo> challengeReviewVos = reviewHelper.findChallengeReviewVos(pageable);
        return challengeMapper.toGetChallengeReviewResponseDto(challengeReviewVos);
    }

    @Override
    public GetChallengeGuidesResponseDto getGuides(Long challengeId) {
        List<ChallengeGuideVo> challengeGuideAdminList = challengeGuideHelper.findAllChallengeGuideAdminVos(challengeId);
        return challengeMapper.toChallengeGuideAdminListResponseDto(challengeGuideAdminList);
    }

    @Override
    public GetChallengeNoticesResponseDto getNotices(Long challengeId, Pageable pageable) {
        Page<ChallengeNoticeVo> challengeNoticeList = challengeNoticeHelper.findAllChallengeNoticeVos(challengeId, pageable);
        return challengeMapper.toGetChallengeNoticesResponseDto(challengeNoticeList);
    }

    @Override
    public GetChallengeDashboardDailyMissionResponseDto getDashboardDailyMission(Long challengeId, User user) {
        challengeApplicationHelper.validateChallengeDashboardAccessibleUser(challengeId, user);
        Challenge challenge = challengeHelper.findChallengeByIdOrThrow(challengeId);
        DailyMissionVo dailyMissionVo = missionHelper.findDailyMissionVoOrNull(challenge.getId());
        AttendanceDailyMissionVo attendanceVo = attendanceHelper.findAttendanceDailyMissionVoOrNull(dailyMissionVo.id(), user.getId());
        return missionMapper.toGetChallengeDashboardDailyMissionResponseDto(dailyMissionVo, attendanceVo);
    }

    @Override
    public void createChallenge(CreateChallengeRequestDto createChallengeRequestDto) {
        ZoomMeetingResponseDto zoomMeetingInfo = zoomUtils.createZoomMeeting(createChallengeRequestDto.title(), createChallengeRequestDto.startDate());
        Challenge challenge = challengeHelper.createChallengeAndSave(createChallengeRequestDto, zoomMeetingInfo);
        createClassificationListAndSave(createChallengeRequestDto.programTypeInfo(), challenge);
        createPriceListAndSave(createChallengeRequestDto.priceInfo(), challenge);
        createFaqListAndSave(createChallengeRequestDto.faqInfo(), challenge);
    }

    @Override
    public void updateChallenge(Long challengeId, UpdateChallengeRequestDto createChallengeRequestDto) {
        Challenge challenge = challengeHelper.findChallengeByIdOrThrow(challengeId);
        challenge.updateChallenge(createChallengeRequestDto);
        updateChallengeClassifications(challenge, createChallengeRequestDto.programTypeInfo());
        updateChallengePrices(challenge, createChallengeRequestDto.priceInfo());
        updateChallengeFaqs(challenge, createChallengeRequestDto.faqInfo());
    }

    @Override
    public void updateApplicationsScore(Long challengeId, Long applicationId, UpdateChallengeApplicationPaybackRequestDto requestDto) {
        AttendanceScore attendanceScore = attendanceScoreHelper.findAttendanceScoreByChallengeIdAndApplicationIdOrThrow(challengeId, applicationId);
        Payment payment = paymentHelper.findPaymentByApplicationIdOrThrow(applicationId);
        attendanceScore.updateAdminScore(requestDto.adminScore());
        payment.updateRefund(requestDto);
    }

    @Override
    public void deleteChallenge(Long challengeId) {
        challengeHelper.deleteChallengeById(challengeId);
    }

    private void createClassificationListAndSave(List<CreateChallengeClassificationRequestDto> requestDtoList,
                                                 Challenge challenge) {
        requestDtoList.stream()
                .map(requestDto -> challengeClassificationHelper.createChallengeClassificationAndSave(requestDto, challenge))
                .collect(Collectors.toList());
    }

    private List<MissionApplicationScoreResponseDto> createMissionApplications(List<UserChallengeApplicationVo> challengeApplicationVos, Long challengeId) {
        return challengeApplicationVos.stream()
                .map(challengeApplicationVo -> createMissionApplicationScoreForUser(challengeApplicationVo, challengeId))
                .collect(Collectors.toList());
    }

    private MissionApplicationScoreResponseDto createMissionApplicationScoreForUser(UserChallengeApplicationVo challengeApplication, Long challengeId) {
        List<AttendanceScoreVo> scores = attendanceHelper.findAttendanceScoreVos(challengeApplication.id(), challengeId);
        Payment payment = paymentHelper.findPaymentByApplicationIdOrThrow(challengeApplication.id());
        return missionMapper.toMissionApplicationScoreResponseDto(challengeApplication, scores, payment);
    }

    private void createPriceListAndSave(List<CreateChallengePriceRequestDto> requestDtoList,
                                        Challenge challenge) {
        requestDtoList.stream()
                .map(requestDto -> challengePriceHelper.createChallengePriceAndSave(requestDto, challenge))
                .collect(Collectors.toList());
    }

    private void createFaqListAndSave(List<CreateProgramFaqRequestDto> requestDtoList,
                                      Challenge challenge) {
        List<Faq> faqs = getFaqsById(requestDtoList);
        faqs.stream().forEach(faq -> faqHelper.createFaqChallengeAndSave(faq, challenge));
    }

    private void updateChallengeClassifications(Challenge challenge, List<CreateChallengeClassificationRequestDto> programInfo) {
        if (Objects.isNull(programInfo)) return;
        challengeClassificationHelper.deleteChallengeClassificationsByChallengeId(challenge.getId());
        challenge.setInitClassificationList();
        createClassificationListAndSave(programInfo, challenge);
    }

    private void updateChallengePrices(Challenge challenge, List<CreateChallengePriceRequestDto> priceInfo) {
        if (Objects.isNull(priceInfo)) return;
        challengePriceHelper.deleteChallengePricesByChallengeId(challenge.getId());
        challenge.setInitPriceList();
        createPriceListAndSave(priceInfo, challenge);
    }

    private void updateChallengeFaqs(Challenge challenge, List<CreateProgramFaqRequestDto> faqInfo) {
        if (Objects.isNull(faqInfo)) return;
        faqHelper.deleteChallengeFaqsByChallengeId(challenge.getId());
        challenge.setInitFaqList();
        createFaqListAndSave(faqInfo, challenge);
    }

    private List<Faq> getFaqsById(List<CreateProgramFaqRequestDto> requestDtoList) {
        return requestDtoList.stream()
                .map(request -> faqHelper.findFaqByIdAndThrow(request.faqId()))
                .collect(Collectors.toList());
    }
}
