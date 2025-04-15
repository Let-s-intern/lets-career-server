package org.letscareer.letscareer.domain.missioncontents.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.contents.entity.Contents;
import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.domain.contents.type.converter.ContentsTypeConverter;
import org.letscareer.letscareer.domain.mission.entity.Mission;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission_contents")
@Entity
public class MissionContents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_contents_id")
    private Long id;
    @Convert(converter = ContentsTypeConverter.class)
    private ContentsType contentsType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contents_id")
    private Contents contents;

    public static MissionContents createMissionContents(Mission mission, Contents contents, ContentsType contentsType) {
        return MissionContents.builder()
                .mission(mission)
                .contents(contents)
                .contentsType(contentsType)
                .build();
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }
}
