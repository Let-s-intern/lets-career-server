package org.letscareer.letscareer.domain.classification.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.vod.entity.Vod;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@DiscriminatorValue("vod_classification")
@Getter
@Entity
public class VodClassification extends Classification {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vod_id")
    private Vod vod;
}
