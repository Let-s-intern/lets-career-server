package org.letscareer.letscareer.domain.missiontemplate.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.missiontemplate.dto.request.CreateMissionTemplateRequestDto;
import org.letscareer.letscareer.domain.missiontemplate.dto.request.UpdateMissionTemplateRequestDto;
import org.letscareer.letscareer.domain.missiontemplate.type.MissionTemplateType;
import org.letscareer.letscareer.domain.missiontemplate.type.converter.MissionTemplateTypeConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

import static org.letscareer.letscareer.global.common.utils.EntityUpdateValueUtils.updateValue;

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
    @Convert(converter = MissionTemplateTypeConverter.class)
    private MissionTemplateType type;

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
                .type(createMissionTemplateRequestDto.type())
                .title(createMissionTemplateRequestDto.title())
                .description(createMissionTemplateRequestDto.description())
                .guide(createMissionTemplateRequestDto.guide())
                .templateLink(createMissionTemplateRequestDto.templateLink())
                .build();
    }

    public void updateMissionTemplate(UpdateMissionTemplateRequestDto updateMissionTemplateRequestDto) {
        this.type = updateValue(this.type, updateMissionTemplateRequestDto.type());
        this.title = updateValue(this.title, updateMissionTemplateRequestDto.title());
        this.description = updateValue(this.description, updateMissionTemplateRequestDto.description());
        this.guide = updateValue(this.guide, updateMissionTemplateRequestDto.guide());
        this.templateLink = updateValue(this.templateLink, updateMissionTemplateRequestDto.templateLink());
    }
}
