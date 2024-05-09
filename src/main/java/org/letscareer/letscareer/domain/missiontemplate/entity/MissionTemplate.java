package org.letscareer.letscareer.domain.missiontemplate.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission_template")
@Entity
public class MissionTemplate extends BaseTimeEntity {

    @Id
    @Column(name = "mission_template_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String desc;

    @NotNull
    private String guide;

    @NotNull
    private String templateLink;

    @OneToMany(mappedBy = "mission_template", fetch = FetchType.LAZY)
    private List<Mission> missionList;

}
