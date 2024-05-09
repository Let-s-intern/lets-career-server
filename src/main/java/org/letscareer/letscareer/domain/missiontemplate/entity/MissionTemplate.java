package org.letscareer.letscareer.domain.missiontemplate.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.contents.entity.Contents;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.missiontemplate.type.MissionTemplateTopic;
import org.letscareer.letscareer.domain.missiontemplate.type.MissionTemplateType;
import org.letscareer.letscareer.domain.missiontemplate.type.converter.MissionTemplateTopicConverter;
import org.letscareer.letscareer.domain.missiontemplate.type.converter.MissionTemplateTypeConverter;
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
    private String guide;

    @NotNull
    private String guideLink;

    @NotNull
    @Convert(converter = MissionTemplateTypeConverter.class)
    private MissionTemplateType type;

    @NotNull
    @Convert(converter = MissionTemplateTopicConverter.class)
    private MissionTemplateTopic topic;

//    @OneToMany(mappedBy = "mission_template_essential", fetch = FetchType.LAZY)
//    private List<Contents> essentialContentsList;
//
//    @OneToMany(mappedBy = "mission_template_additional", fetch = FetchType.LAZY)
//    private List<Contents> additionalContentsList;
//
//    @OneToMany(mappedBy = "mission_template_limited", fetch = FetchType.LAZY)
//    private List<Contents> limitedContentsList;
//
//    @OneToMany(mappedBy = "mission_template", fetch = FetchType.LAZY)
//    private List<Mission> missionList;
}
