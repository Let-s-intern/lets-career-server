package org.letscareer.letscareer.domain.score.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.mission.dto.request.CreateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.entity.Mission;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("mission_score")
@Getter
@Entity
public class MissionScore extends Score {
    private Integer successScore = 0;
    private Integer lateScore = 0;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Builder(access = AccessLevel.PRIVATE)
    public MissionScore(CreateMissionRequestDto createMissionRequestDto, Mission mission) {
        super(createMissionRequestDto);
        this.successScore = createMissionRequestDto.score();
        this.lateScore = createMissionRequestDto.lateScore();
        this.mission = mission;
    }

    public static MissionScore createMissionScore(CreateMissionRequestDto createMissionRequestDto, Mission mission) {
        MissionScore missionScore = MissionScore.builder()
                .createMissionRequestDto(createMissionRequestDto)
                .mission(mission)
                .build();
        mission.setMissionScore(missionScore);
        return missionScore;
    }
}
