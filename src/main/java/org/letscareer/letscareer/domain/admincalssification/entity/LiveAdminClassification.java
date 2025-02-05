package org.letscareer.letscareer.domain.admincalssification.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.admincalssification.request.CreateLiveAdminClassificationRequestDto;
import org.letscareer.letscareer.domain.live.entity.Live;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("live_admin_classification")
@Getter
@Entity
public class LiveAdminClassification extends AdminClassification {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "live_id")
    private Live live;

    @Builder(access = AccessLevel.PRIVATE)
    public LiveAdminClassification(CreateLiveAdminClassificationRequestDto requestDto,
                                   Live live) {
        super(requestDto.classificationInfo());
        this.live = live;
        live.addLiveAdminClassification(this);
    }

    public static LiveAdminClassification createLiveAdminClassification(CreateLiveAdminClassificationRequestDto requestDto,
                                                                        Live live) {
        return LiveAdminClassification.builder()
                .requestDto(requestDto)
                .live(live)
                .build();
    }
}
