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

    public List<MissionForChallengeVo> findMissionForChallengeVos(Long challengeId) {
        return missionRepository.findMissionForChallengeVos(challengeId);
    }

    public Mission findMissionByIdOrThrow(Long missionId) {
        return missionRepository.findById(missionId)
                .orElseThrow(() -> new EntityNotFoundException(MISSION_NOT_FOUND));
    }

    public List<ContentsMissionVo> findContentsMissionVos(Long missionId, ContentsType contentsType) {
        return missionRepository.findMissionContentsVos(missionId, contentsType);
    }

    public void saveMission(Mission mission) {
        missionRepository.save(mission);
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

    public List<MissionScheduleVo> findMissionScheduleVosByChallengeId(Long challengeId) {
        return missionRepository.findMissionScheduleVosByChallengeId(challengeId);
    }

    public List<?> findMyMissionVos(Long challengeId, MissionQueryType queryType, Long userId) {
        if (queryType.equals(MissionQueryType.SUBMITTED))
            return missionRepository.findMySubmittedMissionVosByChallengeIdAndUserId(challengeId, userId);
        else
            return missionRepository.findMyMissionVosByChallengeIdAndUserId(challengeId, queryType, userId);
    }

    public void deleteMission(Long missionId) {
        missionRepository.deleteById(missionId);
    }
}
