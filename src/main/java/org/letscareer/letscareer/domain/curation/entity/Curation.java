package org.letscareer.letscareer.domain.curation.entity;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.curation.dto.request.CreateCurationRequestDto;
import org.letscareer.letscareer.domain.curation.dto.request.UpdateCurationRequestDto;
import org.letscareer.letscareer.domain.curation.type.CurationLocationType;
import org.letscareer.letscareer.domain.curation.type.converter.CurationLocationTypeConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;

import static org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils.updateValue;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Entity
@Tag(name = "Curation")
public class Curation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curation_id")
    private Long id;

    private String title;

    private String subTitle;

    private Integer listSize;

    private String content;

    @Convert(converter = CurationLocationTypeConverter.class)
    private CurationLocationType locationType;

    @Builder.Default
    private Boolean isVisible = false;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public static Curation createCuration(CurationLocationType locationType, CreateCurationRequestDto requestDto) {
        return Curation.builder()
                .locationType(locationType)
                .title(requestDto.title())
                .subTitle(requestDto.subTitle())
                .listSize(requestDto.listSize())
                .content(requestDto.content())
                .startDate(requestDto.startDate())
                .endDate(requestDto.endDate())
                .build();
    }

    public void updateCuration(UpdateCurationRequestDto requestDto) {
        this.title = updateValue(this.title, requestDto.title());
        this.subTitle = updateValue(this.subTitle, requestDto.subTitle());
        this.listSize = updateValue(this.listSize, requestDto.listSize());
        this.content = updateValue(this.content, requestDto.content());
        this.startDate = updateValue(this.startDate, requestDto.startDate());
        this.endDate = updateValue(this.endDate, requestDto.endDate());
        this.locationType = updateValue(this.locationType, requestDto.locationType());
        this.isVisible = updateValue(this.isVisible, requestDto.isVisible());
    }
}
