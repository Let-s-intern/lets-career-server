package org.letscareer.letscareer.domain.curation.entity;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.curation.type.CurationLocationType;
import org.letscareer.letscareer.domain.curation.type.converter.CurationLocationTypeConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;

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
}
