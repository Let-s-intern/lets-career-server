package org.letscareer.letscareer.domain.curation.entity;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.curation.dto.request.CreateCurationItemRequestDto;
import org.letscareer.letscareer.domain.curation.type.CurationItemProgramType;
import org.letscareer.letscareer.domain.curation.type.converter.CurationItemProgramTypeConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Entity
@Tag(name = "CurationItem")
public class CurationItem extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curation_item_id")
    private Long id;

    @Convert(converter = CurationItemProgramTypeConverter.class)
    private CurationItemProgramType programType;

    private Long programId;

    private String tag;

    private String title;

    private String thumbnail;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curation_id")
    private Curation curation;

    public static CurationItem createCurationItem(Curation curation, CreateCurationItemRequestDto requestDto) {
        if(requestDto.programType().equals(CurationItemProgramType.ETC)) {
            return CurationItem.builder()
                    .curation(curation)
                    .programType(CurationItemProgramType.ETC)
                    .tag(requestDto.tag())
                    .title(requestDto.title())
                    .thumbnail(requestDto.thumbnail())
                    .url(requestDto.url())
                    .build();
        }
        return CurationItem.builder()
                .curation(curation)
                .programType(requestDto.programType())
                .programId(requestDto.programId())
                .tag(requestDto.tag())
                .build();
    }
}
