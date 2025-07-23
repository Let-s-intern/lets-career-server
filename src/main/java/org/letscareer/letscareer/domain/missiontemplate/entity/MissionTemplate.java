package org.letscareer.letscareer.domain.missiontemplate.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.missiontemplate.dto.request.CreateMissionTemplateRequestDto;
import org.letscareer.letscareer.domain.missiontemplate.dto.request.UpdateMissionTemplateRequestDto;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

import static org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils.updateValue;

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
    private String missionTag;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String guide;
    private String templateLink;
    private String vodLink;

    @OneToMany(mappedBy = "missionTemplate", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Mission> missionList = new ArrayList<>();

    public static MissionTemplate createMissionTemplate(CreateMissionTemplateRequestDto createMissionTemplateRequestDto) {
        return MissionTemplate.builder()
                .missionTag(createMissionTemplateRequestDto.missionTag())
                .title(createMissionTemplateRequestDto.title())
                .description(createMissionTemplateRequestDto.description())
                .guide(createMissionTemplateRequestDto.guide())
                .templateLink(createMissionTemplateRequestDto.templateLink())
                .vodLink(createMissionTemplateRequestDto.vodLink())
                .build();
    }

    public void updateMissionTemplate(UpdateMissionTemplateRequestDto updateMissionTemplateRequestDto) {
        this.missionTag = updateValue(this.missionTag, updateMissionTemplateRequestDto.missionTag());
        this.title = updateValue(this.title, updateMissionTemplateRequestDto.title());
        this.description = updateValue(this.description, updateMissionTemplateRequestDto.description());
        this.guide = updateValue(this.guide, updateMissionTemplateRequestDto.guide());
        this.templateLink = updateValue(this.templateLink, updateMissionTemplateRequestDto.templateLink());
        this.vodLink = updateValue(this.vodLink, updateMissionTemplateRequestDto.vodLink());
    }
}
