package org.letscareer.letscareer.domain.classification.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.classification.dto.request.CreateLiveClassificationRequestDto;
import org.letscareer.letscareer.domain.live.entity.Live;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("live_classification")
@Getter
@Entity
public class LiveClassification extends Classification {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "live_id")
    private Live live;

    @Builder(access = AccessLevel.PRIVATE)
    public LiveClassification(CreateLiveClassificationRequestDto requestDto,
                              Live live) {
        super(requestDto.classificationInfo());
        this.live = live;
        live.addLiveClassification(this);
    }

    public static LiveClassification createLiveClassification(CreateLiveClassificationRequestDto requestDto,
                                                              Live live) {
        return LiveClassification.builder()
                .requestDto(requestDto)
                .live(live)
                .build();
    }
}
