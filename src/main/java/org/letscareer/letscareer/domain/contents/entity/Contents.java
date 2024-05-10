package org.letscareer.letscareer.domain.contents.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.contents.dto.request.CreateContentsRequestDto;
import org.letscareer.letscareer.domain.contents.dto.request.UpdateContentsRequestDto;
import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.domain.contents.type.converter.ContentsTypeConverter;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import static org.letscareer.letscareer.global.common.utils.EntityUpdateValueUtils.updateValue;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "essential_contents_id")
    private Mission missionEssential;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "additional_contents_id")
    private Mission missionAdditional;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "limited_contents_id")
    private Mission missionLimited;

    public static Contents createContents(CreateContentsRequestDto createContentsRequestDto) {
        return Contents.builder()
                .type(createContentsRequestDto.type())
                .title(createContentsRequestDto.title())
                .link(createContentsRequestDto.link())
                .build();
    }

    public void updateContents(UpdateContentsRequestDto updateContentsRequestDto) {
        this.type = updateValue(this.type, updateContentsRequestDto.type());
        this.title = updateValue(this.title, updateContentsRequestDto.title());
        this.link = updateValue(this.link, updateContentsRequestDto.link());
    }
}
