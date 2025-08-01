package org.letscareer.letscareer.domain.mission.repository;

import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.mission.type.MissionQueryType;
import org.letscareer.letscareer.domain.mission.vo.*;
import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.domain.contents.vo.ContentsMissionVo;

import java.util.List;
import java.util.Optional;

public interface MissionQueryRepository {
    List<MissionForChallengeVo> findMissionForChallengeVos(Long challengeId);
    List<Mission> findMissionsByChallengeId(Long challengeId);
    List<FeedbackMissionAdminVo> findFeedbackMissionAdminVodByChallengeId(Long challengeId);
    Optional<DailyMissionVo> findDailyMissionVoByChallengeId(Long challengeId);
    Optional<MyDailyMissionVo> findMyDailyMissionVoByChallengeId(Long challengeId);
    Optional<MyDailyMissionVo> findMyDailyMissionVoByMissionId(Long missionId);
    Optional<MyMissionFeedbackVo> findMyMissionFeedbackVoByMissionId(Long missionId);
    List<ContentsMissionVo> findMissionContentsVos(Long missionId, ContentsType contentsType);
    List<MissionScheduleVo> findMissionScheduleVosByChallengeId(Long challengeId);
    List<MySubmittedMissionVo> findMySubmittedMissionVosByChallengeIdAndUserId(Long challengeId, Long userId);
    List<MyMissionVo> findMyMissionVosByChallengeIdAndUserId(Long challengeId, MissionQueryType queryType, Long userId);
    Optional<Integer> finsSumOfMissionScoresByChallengeId(Long challengeId);
    Optional<Integer> findApplicationScoreByMissionId(Long missionId, Long applicationId);
    Optional<Integer> findAttendanceScoreByMissionIdAndUserId(Long challengeId, Long userId);
    Optional<MissionDetailVo> findMissionDetailVo(Long missionId);
    List<Long> findALlEndNotificationMissionId();
}
