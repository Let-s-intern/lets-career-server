package org.letscareer.letscareer.domain.classification.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.live.entity.Live;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@DiscriminatorValue("live_classification")
@Getter
@Entity
public class LiveClassification extends Classification {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "live_id")
    private Live live;
}
