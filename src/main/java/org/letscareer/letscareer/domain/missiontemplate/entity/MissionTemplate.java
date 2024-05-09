package org.letscareer.letscareer.domain.missiontemplate.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.missiontemplate.dto.request.CreateMissionTemplateRequestDto;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
    private String description;

    @NotNull
    private String guide;

    @NotNull
    private String templateLink;

    @OneToMany(mappedBy = "missionTemplate", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Mission> missionList = new ArrayList<>();

    public static MissionTemplate createMissionTemplate(CreateMissionTemplateRequestDto createMissionTemplateRequestDto) {
        return MissionTemplate.builder()
                .title(createMissionTemplateRequestDto.title())
                .description(createMissionTemplateRequestDto.description())
                .guide(createMissionTemplateRequestDto.guide())
                .templateLink(createMissionTemplateRequestDto.templateLink())
                .build();
    }
}
