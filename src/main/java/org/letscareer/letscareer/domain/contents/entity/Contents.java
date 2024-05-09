package org.letscareer.letscareer.domain.contents.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.contents.dto.request.CreateContentsRequestDto;
import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.domain.contents.type.converter.ContentsTypeConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contents extends BaseTimeEntity {

    @Id
    @Column(name = "contents_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Convert(converter = ContentsTypeConverter.class)
    private ContentsType type;

    @NotNull
    private String title;

    @NotNull
    private String link;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "contents_id")
//    private MissionTemplate missionTemplate;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "contents_id")
//    private MissionTemplate missionTemplateAdditional;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "contents_id")
//    private MissionTemplate missionTemplateLimited;

    public static Contents createContents(CreateContentsRequestDto createContentsRequestDto) {
        return Contents.builder()
                .type(createContentsRequestDto.type())
                .title(createContentsRequestDto.title())
                .link(createContentsRequestDto.link())
                .build();
    }
}
