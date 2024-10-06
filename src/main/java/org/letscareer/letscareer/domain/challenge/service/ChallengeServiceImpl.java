package org.letscareer.letscareer.domain.challenge.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.response.GetChallengeApplicationsResponseDto;
import org.letscareer.letscareer.domain.application.helper.ChallengeApplicationHelper;
import org.letscareer.letscareer.domain.application.mapper.ChallengeApplicationMapper;
import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationVo;
import org.letscareer.letscareer.domain.application.vo.UserChallengeApplicationVo;
import org.letscareer.letscareer.domain.attendance.helper.AttendanceHelper;
import org.letscareer.letscareer.domain.attendance.mapper.AttendanceMapper;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceDashboardVo;
import org.letscareer.letscareer.domain.attendance.vo.MissionAttendanceVo;
import org.letscareer.letscareer.domain.attendance.vo.MissionScoreVo;
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
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.faq.dto.request.CreateProgramFaqRequestDto;
import org.letscareer.letscareer.domain.faq.dto.response.GetFaqResponseDto;
import org.letscareer.letscareer.domain.faq.entity.Faq;
import org.letscareer.letscareer.domain.faq.helper.FaqHelper;
import org.letscareer.letscareer.domain.faq.mapper.FaqMapper;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;
import org.letscareer.letscareer.domain.mission.dto.response.MissionApplicationScoreResponseDto;
import org.letscareer.letscareer.domain.mission.dto.response.MissionScoreResponseDto;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.mission.helper.MissionHelper;
import org.letscareer.letscareer.domain.mission.mapper.MissionMapper;
import org.letscareer.letscareer.domain.mission.type.MissionQueryType;
import org.letscareer.letscareer.domain.mission.vo.DailyMissionVo;
import org.letscareer.letscareer.domain.mission.vo.MissionScheduleVo;
import org.letscareer.letscareer.domain.mission.vo.MyDailyMissionVo;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.helper.PaymentHelper;
import org.letscareer.letscareer.domain.price.dto.request.CreateChallengePriceRequestDto;
import org.letscareer.letscareer.domain.price.helper.ChallengePriceHelper;
import org.letscareer.letscareer.domain.price.vo.ChallengePriceDetailVo;
import org.letscareer.letscareer.domain.program.dto.response.ZoomMeetingResponseDto;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewResponseDto;
import org.letscareer.letscareer.domain.review.helper.ReviewHelper;
import org.letscareer.letscareer.domain.review.mapper.ReviewMapper;
import org.letscareer.letscareer.domain.review.vo.ReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.letscareer.letscareer.domain.score.entity.AdminScore;
import org.letscareer.letscareer.domain.score.helper.AdminScoreHelper;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.letscareer.letscareer.global.common.utils.zoom.ZoomUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
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
    private final ChallengeNoticeHelper challengeNoticeHelper;
    private final ChallengePriceHelper challengePriceHelper;
    private final ChallengeGuideHelper challengeGuideHelper;
    private final AttendanceHelper attendanceHelper;
    private final AttendanceMapper attendanceMapper;
    private final AdminScoreHelper adminScoreHelper;
    private final MissionHelper missionHelper;
    private final MissionMapper missionMapper;
    private final PaymentHelper paymentHelper;
    private final ReviewHelper reviewHelper;
    private final ReviewMapper reviewMapper;
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
    public GetChallengeApplicationsResponseDto getApplications(Long challengeId, Boolean isCanceled) {
        List<AdminChallengeApplicationVo> applicationVos = challengeApplicationHelper.findAdminChallengeApplicationVos(challengeId, isCanceled);
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
        List<GetReviewResponseDto> reviewResDtoList = createGetReviewResponseDtoList(challengeReviewVos.getContent());
        PageInfo pageInfo = PageInfo.of(challengeReviewVos);
        return challengeMapper.toGetChallengeReviewResponseDto(reviewResDtoList, pageInfo);
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
    public GetChallengeTotalScoreResponseDto getTotalScore(Long challengeId, Long userId) {
        List<Mission> missionList = missionHelper.findMissionsByChallengeId(challengeId);
        Long applicationId = challengeApplicationHelper.findApplicationIdByChallengeIdAndUserId(challengeId, userId);
        Integer currentScore = getMissionTotalScoreForUser(missionList, applicationId);
        AdminScore adminScore = adminScoreHelper.findAdminScoreByChallengeIdAndApplicationIdOrThrow(challengeId, applicationId);
        Integer totalScore = missionHelper.finsSumOfMissionScoresByChallengeId(challengeId);
        return missionMapper.toGetChallengeTotalScoreResponseDto(currentScore + adminScore.getScore(), totalScore);
    }

    private Integer getMissionTotalScoreForUser(List<Mission> missionList, Long applicationId) {
        return missionList.stream()
                .mapToInt(mission -> missionHelper.findApplicationScoreByMissionIdOrZero(mission.getId(), applicationId))
                .sum();
    }

    @Override
    public GetChallengeScheduleResponseDto getSchedule(Long challengeId, Long userId) {
        List<MissionScheduleVo> missionScheduleVoList = missionHelper.findMissionScheduleVosByChallengeId(challengeId);
        List<ChallengeScheduleVo> challengeScheduleVoList = missionScheduleVoList.stream()
                .map(missionScheduleVo -> new ChallengeScheduleVo(
                        missionScheduleVo,
                        attendanceHelper.findAttendanceDashboardVoOrNull(missionScheduleVo.id(), userId))
                ).toList();
        return missionMapper.toGetChallengeScheduleResponseDto(challengeScheduleVoList);
    }

    @Override
    public GetChallengeDailyMissionResponseDto getDailyMission(Long challengeId, User user) {
        challengeApplicationHelper.validateChallengeDashboardAccessibleUser(challengeId, user);
        Challenge challenge = challengeHelper.findChallengeByIdOrThrow(challengeId);
        DailyMissionVo dailyMissionVo = missionHelper.findDailyMissionVoOrNull(challenge.getId());
        return missionMapper.toGetChallengeDailyMissionResponseDto(dailyMissionVo);
    }

    @Override
    public GetChallengeMyDailyMissionResponseDto getDashboardDailyMission(Long challengeId, User user) {
        challengeApplicationHelper.validateChallengeDashboardAccessibleUser(challengeId, user);
        Challenge challenge = challengeHelper.findChallengeByIdOrThrow(challengeId);
        MyDailyMissionVo myDailyMissionVo = missionHelper.findMyDailyMissionVoOrNull(challenge.getId());
        AttendanceDashboardVo attendanceDashboardVo = myDailyMissionVo != null ? attendanceHelper.findAttendanceDashboardVoOrNull(myDailyMissionVo.id(), user.getId()) : null;
        return missionMapper.toGetChallengeMyDailyMissionResponseDto(myDailyMissionVo, attendanceDashboardVo);
    }

    @Override
    public GetChallengeMyMissionsResponseDto getMyMissions(Long challengeId, MissionQueryType queryType, User user) {
        challengeApplicationHelper.validateChallengeDashboardAccessibleUser(challengeId, user);
        List<?> missionVoList = missionHelper.findMyMissionVos(challengeId, queryType, user.getId());
        return missionMapper.toGetChallengeMyMissionsResponseDto(missionVoList);
    }

    @Override
    public GetChallengeMyMissionDetailResponseDto getMyMissionDetail(Long challengeId, Long missionId, User user) {
        challengeApplicationHelper.validateChallengeDashboardAccessibleUser(challengeId, user);
        MyDailyMissionVo missionInfo = missionHelper.findMyDailyMissionVoByMissionId(missionId);
        AttendanceDashboardVo attendanceInfo = missionInfo != null ? attendanceHelper.findAttendanceDashboardVoOrNull(missionInfo.id(), user.getId()) : null;
        return missionMapper.toGetChallengeMyMissionDetailResponseDto(missionInfo, attendanceInfo);
    }

    @Override
    public GetChallengeApplicationEmailListResponseDto getApplicationEmails(Long challengeId) {
        List<String> emailList = challengeApplicationHelper.getValidApplicationEmailList(challengeId);
        return challengeApplicationMapper.toGetChallengeApplicationEmailListResponseDto(emailList);
    }

    @Override
    public GetChallengeEmailContentsResponseDto getEmailContents(Long challengeId) {
        Challenge challenge = challengeHelper.findChallengeByIdOrThrow(challengeId);
        String title = challengeHelper.createChallengeMailTitle(challenge);
        String contents = challengeHelper.createChallengeMailContents(challenge);
        return challengeMapper.toGetChallengeEmailContentsResponseDto(title, contents);
    }

    @Override
    public GetChallengeAccessResponseDto checkChallengeDashboardAccessibleUser(Long challengeId, Long userId) {
        Boolean applied = challengeApplicationHelper.existChallengeApplicationByChallengeIdAndUserId(challengeId, userId);
        Boolean isRefunded = paymentHelper.checkIsRefundedForChallenge(challengeId, userId);
        return challengeApplicationMapper.toGetChallengeAccessResponseDto(applied, isRefunded);
    }

    @Override
    public GetChallengeExisingApplicationResponseDto getChallengeExistingApplication(Long challengeId, Long userId) {
        Boolean applied = challengeApplicationHelper.existChallengeApplicationByChallengeIdAndUserId(challengeId, userId);
        return challengeApplicationMapper.toChallengeExistingApplicationResponseDto(applied);
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
        AdminScore adminScore = adminScoreHelper.findAdminScoreByChallengeIdAndApplicationIdOrThrow(challengeId, applicationId);
        adminScore.UpdateAdminScore(requestDto.adminScore());
        Payment payment = paymentHelper.findPaymentByApplicationIdOrThrow(applicationId);
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

    private List<GetReviewResponseDto> createGetReviewResponseDtoList(List<ReviewVo> vos) {
        return vos.stream()
                .map(vo -> reviewMapper.toGetReviewResponseDto(vo, challengeHelper.findChallengeTitleVoOrThrow(vo.id()).title()))
                .collect(Collectors.toList());
    }

    private List<MissionApplicationScoreResponseDto> createMissionApplications(List<UserChallengeApplicationVo> challengeApplicationVos, Long challengeId) {
        return challengeApplicationVos.stream()
                .map(challengeApplicationVo -> createMissionApplicationScoreForUser(challengeApplicationVo, challengeId))
                .collect(Collectors.toList());
    }

    private MissionApplicationScoreResponseDto createMissionApplicationScoreForUser(UserChallengeApplicationVo challengeApplication, Long challengeId) {
        List<MissionScoreVo> scores = attendanceHelper.findAttendanceScoreVos(challengeApplication.id(), challengeId);
        List<MissionScoreResponseDto> scoreResponseDtoList = createMissionScoreResponseDtoList(scores, challengeId, challengeApplication.id());
        Payment payment = paymentHelper.findPaymentByApplicationIdOrThrow(challengeApplication.id());
        Coupon coupon = payment.getCoupon();
        return missionMapper.toMissionApplicationScoreResponseDto(challengeApplication, scoreResponseDtoList, payment, coupon);
    }

    private List<MissionScoreResponseDto> createMissionScoreResponseDtoList(List<MissionScoreVo> scores, Long challengeId, Long applicationId) {
        List<MissionScoreResponseDto> contents = scores.stream()
                .map(score -> createMissionScoreResponseDto(score, applicationId))
                .collect(Collectors.toList());
        AdminScore adminScore = adminScoreHelper.findAdminScoreByChallengeIdAndApplicationIdOrThrow(challengeId, applicationId);
        MissionScoreResponseDto missionScoreResponseDto = missionMapper.toMissionScoreResponseDto(99, adminScore.getScore());
        contents.add(missionScoreResponseDto);
        contents.sort(Comparator.comparing(MissionScoreResponseDto::th));
        return contents;
    }

    private MissionScoreResponseDto createMissionScoreResponseDto(MissionScoreVo score, Long applicationId) {
        Integer totalScore = missionHelper.findApplicationScoreByMissionIdOrZero(score.missionId(), applicationId);
        return missionMapper.toMissionScoreResponseDto(score.th(), totalScore);
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
