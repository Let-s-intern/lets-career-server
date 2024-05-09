package org.letscareer.letscareer.domain.classification.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.classification.dto.request.CreateVodClassificationRequestDto;
import org.letscareer.letscareer.domain.vod.entity.Vod;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("vod_classification")
@Getter
@Entity
public class VodClassification extends Classification {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vod_id")
    private Vod vod;

    @Builder(access = AccessLevel.PRIVATE)
    public VodClassification(CreateVodClassificationRequestDto requestDto,
                             Vod vod) {
        super(requestDto.classificationInfo());
        this.vod = vod;
        this.vod.addClassification(this);
    }

    public static VodClassification createVodClassification(CreateVodClassificationRequestDto requestDto,
                                                            Vod vod) {
        return VodClassification.builder()
                .requestDto(requestDto)
                .vod(vod)
                .build();
    }
}
