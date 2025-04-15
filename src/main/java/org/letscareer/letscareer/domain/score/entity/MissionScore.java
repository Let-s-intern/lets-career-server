package org.letscareer.letscareer.domain.score.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.mission.dto.request.CreateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.dto.request.UpdateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.entity.Mission;

import static org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils.updateValue;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@DiscriminatorValue("mission_score")
@Getter
@Entity
public class MissionScore extends Score {
    private Integer successScore = 0;
    private Integer lateScore = 0;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    public static MissionScore createMissionScore(CreateMissionRequestDto createMissionRequestDto, Mission mission) {
        MissionScore missionScore = MissionScore.builder()
                .successScore(createMissionRequestDto.score())
                .lateScore(createMissionRequestDto.lateScore())
                .mission(mission)
                .build();
        mission.setMissionScore(missionScore);
        return missionScore;
    }

    public static MissionScore copyMissionScore(MissionScore missionScore, Mission mission) {
        MissionScore copiedMissionScore = MissionScore.builder()
                .successScore(missionScore.getSuccessScore())
                .lateScore(missionScore.getLateScore())
                .mission(mission)
                .build();
        mission.setMissionScore(copiedMissionScore);
        return copiedMissionScore;
    }

    public void updateMissionScore(UpdateMissionRequestDto requestDto) {
        this.successScore = updateValue(this.successScore, requestDto.score());
        this.lateScore = updateValue(this.lateScore, requestDto.lateScore());
    }
}
