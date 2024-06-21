package org.letscareer.letscareer.domain.challenge.service;

import org.letscareer.letscareer.domain.application.dto.response.GetChallengeApplicationsResponseDto;
import org.letscareer.letscareer.domain.challenge.dto.request.CreateChallengeRequestDto;
import org.letscareer.letscareer.domain.challenge.dto.request.UpdateChallengeApplicationPaybackRequestDto;
import org.letscareer.letscareer.domain.challenge.dto.request.UpdateChallengeRequestDto;
import org.letscareer.letscareer.domain.challenge.dto.response.*;
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
    GetChallengeResponseDto getChallengeList(List<ProgramClassification> typeList, List<ProgramStatusType> statusList, Pageable pageable);

    GetChallengeDetailResponseDto getChallengeDetail(Long challengeId);

    GetChallengeTitleResponseDto getChallengeTitle(Long challengeId);

    GetChallengeApplicationsResponseDto getApplications(Long challengeId, Boolean isConfirmed);

    GetChallengeApplicationsPaybackResponseDto getApplicationsScore(Long challengeId, Pageable pageable);

    GetChallengeApplicationFormResponseDto getChallengeApplicationForm(User user, Long challengeId);

    GetChallengeThumbnailResponseDto getChallengeThumbnail(Long challengeId);

    GetChallengeContentResponseDto getChallengeDetailContent(Long challengeId);

    GetFaqResponseDto getChallengeFaqs(Long challengeId);

    GetChallengeMissionAttendancesResponseDto getMissionAttendances(Long challengeId, Long missionId);

    GetChallengeAdminReviewResponseDto getReviewsForAdmin(Long challengeId, Pageable pageable);

    GetChallengeReviewResponseDto getReviews(Pageable pageable);

    GetChallengeGuidesResponseDto getGuides(Long challengeId);

    GetChallengeNoticesResponseDto getNotices(Long challengeId, Pageable pageable);

    GetChallengeTotalScoreResponseDto getTotalScore(Long challengeId, Long userId);

    GetChallengeScheduleResponseDto getSchedule(Long challengeId, Long userId);

    GetChallengeDailyMissionResponseDto getDailyMission(Long challengeId, User user);

    GetChallengeMyDailyMissionResponseDto getDashboardDailyMission(Long challengeId, User user);

    GetChallengeMyMissionsResponseDto getMyMissions(Long challengeId, MissionQueryType queryType, User user);

    GetChallengeMyMissionDetailResponseDto getMyMissionDetail(Long challengeId, Long missionId, User user);

    GetChallengeApplicationEmailListResponseDto getApplicationEmails(Long challengeId);

    GetChallengeEmailContentsResponseDto getEmailContents(Long challengeId);

    void createChallenge(CreateChallengeRequestDto createChallengeRequestDto);

    void updateChallenge(Long challengeId, UpdateChallengeRequestDto createChallengeRequestDto);

    void updateApplicationsScore(Long challengeId, Long applicationId, UpdateChallengeApplicationPaybackRequestDto requestDto);

    void deleteChallenge(Long challengeId);

    void validateChallengeDashboardAccessibleUser(Long challengeId, User user);
}
