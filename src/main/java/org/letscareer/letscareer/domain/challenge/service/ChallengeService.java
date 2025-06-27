package org.letscareer.letscareer.domain.challenge.service;

import org.letscareer.letscareer.domain.challenge.dto.request.UpdateChallengeApplicationRequestDto;
import org.letscareer.letscareer.domain.application.dto.response.GetChallengeApplicationsResponseDto;
import org.letscareer.letscareer.domain.challenge.dto.request.CreateChallengeRequestDto;
import org.letscareer.letscareer.domain.challenge.dto.request.UpdateChallengeApplicationPaybackRequestDto;
import org.letscareer.letscareer.domain.challenge.dto.request.UpdateChallengeApplicationPaybacksRequestDto;
import org.letscareer.letscareer.domain.challenge.dto.request.UpdateChallengeRequestDto;
import org.letscareer.letscareer.domain.challenge.dto.response.*;
import org.letscareer.letscareer.domain.challenge.type.ChallengeType;
import org.letscareer.letscareer.domain.challengementor.dto.request.CreateChallengeMentorsRequestDto;
import org.letscareer.letscareer.domain.challengementor.dto.response.GetChallengeMentorsResponseDto;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.faq.dto.response.GetFaqResponseDto;
import org.letscareer.letscareer.domain.mission.type.MissionQueryType;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChallengeService {
    GetChallengeResponseDto getChallengeList(List<ProgramClassification> typeList, List<ProgramStatusType> statusList, ChallengeType type, Pageable pageable);
    GetChallengeResponseDto getHomeChallengeList(List<ProgramClassification> typeList, List<ProgramStatusType> statusList, ChallengeType type, Pageable pageable);

    GetTypeChallengeResponseDto getTypeChallengeList(ChallengeType challengeType);

    GetChallengeDetailResponseDto getChallengeDetail(Long challengeId);

    GetChallengeTitleResponseDto getChallengeTitle(Long challengeId);

    GetChallengeApplicationsResponseDto getApplications(Long challengeId, Boolean isCanceled);

    GetChallengeApplicationsPaybackResponseDto getApplicationsScore(Long challengeId, Pageable pageable);

    GetChallengeApplicationFormResponseDto getChallengeApplicationForm(User user, Long challengeId);

    GetChallengeThumbnailResponseDto getChallengeThumbnail(Long challengeId);

    GetChallengeContentResponseDto getChallengeDetailContent(Long challengeId);

    GetFaqResponseDto getChallengeFaqs(Long challengeId);

    GetChallengeMissionAttendancesResponseDto getMissionAttendances(Long challengeId, Long missionId);

    GetChallengeFeedbackMissionAttendancesResponseDto getFeedbackMissionAttendances(Long challengeId, Long missionId);

    GetChallengeFeedbackMissionAttendancesResponseDto getFeedbackMissionAttendancesForMentor(Long challengeId, Long missionId, User user);

    GetChallengeFeedbackMissionAttendanceResponseDto getFeedbackMissionAttendanceForMentor(Long challengeId, Long missionId, Long attendanceId, User user);

    GetChallengeAdminReviewResponseDto getReviewsForAdmin(Long challengeId, Pageable pageable);

    GetChallengeReviewResponseDto getReviews(Pageable pageable);

    GetChallengeGuidesResponseDto getGuides(Long challengeId);

    GetChallengeNoticesResponseDto getNotices(Long challengeId, Pageable pageable);

    GetChallengeGoalResponseDto getGoal(Long challengeId, Long userId);

    GetChallengeTotalScoreResponseDto getTotalScore(Long challengeId, Long userId);

    GetChallengeScheduleResponseDto getSchedule(Long challengeId, Long userId);

    GetChallengeDailyMissionResponseDto getDailyMission(Long challengeId, User user);

    GetChallengeMyDailyMissionResponseDto getDashboardDailyMission(Long challengeId, User user);

    GetChallengeMyMissionsResponseDto getMyMissions(Long challengeId, MissionQueryType queryType, User user);

    GetChallengeMyMissionDetailResponseDto getMyMissionDetail(Long challengeId, Long missionId, User user);
    GetChallengeMyMissionFeedbackDetailResponseDto getMyMissionFeedbackDetail(Long challengeId, Long missionId, User user);

    GetChallengeApplicationEmailListResponseDto getApplicationEmails(Long challengeId);

    GetChallengeEmailContentsResponseDto getEmailContents(Long challengeId);

    GetChallengeAccessResponseDto checkChallengeDashboardAccessibleUser(Long challengeId, Long userId);

    GetChallengeExisingApplicationResponseDto getChallengeExistingApplication(Long challengeId, Long userId);

    GetChallengeReviewStatusResponseDto getChallengeReviewStatus(Long challengeId, Long userId);

    GetChallengeMentorsResponseDto getChallengeMentors(Long challengeId);

    void createChallenge(CreateChallengeRequestDto createChallengeRequestDto);

    void updateChallenge(Long challengeId, UpdateChallengeRequestDto createChallengeRequestDto);

    void updateApplicationsScore(Long challengeId, Long applicationId, UpdateChallengeApplicationPaybackRequestDto requestDto);

    void paybackChallengeApplications(Long challengeId, UpdateChallengeApplicationPaybacksRequestDto requestDto);

    void updateGoal(Long challengeId, UpdateChallengeApplicationRequestDto requestDto, Long userId);

    void deleteChallenge(Long challengeId);

    void copyChallengeDashBoard(Long fromChallengeId, Long toChallengeId);

    void createChallengeMentors(Long challengeId, CreateChallengeMentorsRequestDto requestDto);
}
