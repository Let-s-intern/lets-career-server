package org.letscareer.letscareer.domain.mission.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.domain.contents.vo.ContentsMissionVo;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.mission.repository.MissionRepository;
import org.letscareer.letscareer.domain.mission.type.MissionQueryType;
import org.letscareer.letscareer.domain.mission.vo.*;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.letscareer.letscareer.domain.mission.error.MissionErrorCode.MISSION_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class MissionHelper {
    private final MissionRepository missionRepository;

    public MissionDetailVo findMissionDetailVoOrThrow(Long missionId) {
        return missionRepository.findMissionDetailVo(missionId)
                .orElseThrow(() -> new EntityNotFoundException(MISSION_NOT_FOUND));
    }

    public List<MissionForChallengeVo> findMissionForChallengeVos(Long challengeId) {
        return missionRepository.findMissionForChallengeVos(challengeId);
    }

    public List<Mission> findMissionsByChallengeId(Long challengeId) {
        return missionRepository.findMissionsByChallengeId(challengeId);
    }

    public List<FeedbackMissionAdminVo> findFeedbackMissionAdminVodByChallengeId(Long challengeId) {
        return missionRepository.findFeedbackMissionAdminVodByChallengeId(challengeId);
    }

    public Mission findMissionByIdOrThrow(Long missionId) {
        return missionRepository.findById(missionId)
                .orElseThrow(() -> new EntityNotFoundException(MISSION_NOT_FOUND));
    }

    public List<ContentsMissionVo> findContentsMissionVos(Long missionId, ContentsType contentsType) {
        return missionRepository.findMissionContentsVos(missionId, contentsType);
    }

    public DailyMissionVo findDailyMissionVoOrNull(Long challengeId) {
        return missionRepository.findDailyMissionVoByChallengeId(challengeId).orElse(null);
    }

    public MyDailyMissionVo findMyDailyMissionVoOrNull(Long challengeId) {
        return missionRepository.findMyDailyMissionVoByChallengeId(challengeId).orElse(null);
    }

    public MyDailyMissionVo findMyDailyMissionVoByMissionId(Long missionId) {
        return missionRepository.findMyDailyMissionVoByMissionId(missionId)
                .orElseThrow(() -> new EntityNotFoundException(MISSION_NOT_FOUND));
    }

    public MyMissionFeedbackVo findMyMissionFeedbackVoByMissionId(Long missionId) {
        return missionRepository.findMyMissionFeedbackVoByMissionId(missionId)
                .orElseThrow(() -> new EntityNotFoundException(MISSION_NOT_FOUND));
    }

    public List<MissionScheduleVo> findMissionScheduleVosByChallengeId(Long challengeId) {
        return missionRepository.findMissionScheduleVosByChallengeId(challengeId);
    }

    public Integer finsSumOfMissionScoresByChallengeId(Long challengeId) {
        return missionRepository.finsSumOfMissionScoresByChallengeId(challengeId)
                .orElse(0);
    }

    public Integer findApplicationScoreByMissionIdOrZero(Long missionId, Long applicationId) {
        return missionRepository.findApplicationScoreByMissionId(missionId, applicationId)
                .orElse(0);
    }

    public Integer findAttendanceScoreByMissionIdAndUserId(Long missionId, Long userId) {
        return missionRepository.findAttendanceScoreByMissionIdAndUserId(missionId, userId)
                .orElse(0);
    }

    public List<?> findMyMissionVos(Long challengeId, MissionQueryType queryType, Long userId) {
        if (queryType.equals(MissionQueryType.SUBMITTED))
            return missionRepository.findMySubmittedMissionVosByChallengeIdAndUserId(challengeId, userId);
        else
            return missionRepository.findMyMissionVosByChallengeIdAndUserId(challengeId, queryType, userId);
    }

    public void saveMission(Mission mission) {
        missionRepository.save(mission);
    }

    public void deleteMission(Long missionId) {
        missionRepository.deleteById(missionId);
    }

    public List<Long> findEndNotificationMissionIds() {
        return missionRepository.findALlEndNotificationMissionId();
    }

    public void deleteAllByChallengeId(Long challengeId) {
        missionRepository.deleteAllByChallengeId(challengeId);
    }

    public boolean hasOTMission(Long challengeId) {
        return missionRepository.existsByChallengeIdAndTh(challengeId, 0);
    }
}
