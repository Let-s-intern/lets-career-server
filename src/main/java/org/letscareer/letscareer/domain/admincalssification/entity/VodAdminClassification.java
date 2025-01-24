package org.letscareer.letscareer.domain.admincalssification.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.admincalssification.request.CreateVodAdminClassificationRequestDto;
import org.letscareer.letscareer.domain.vod.entity.Vod;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("vod_admin_classification")
@Getter
@Entity
public class VodAdminClassification extends AdminClassification {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vod_id")
    private Vod vod;

    @Builder(access = AccessLevel.PRIVATE)
    public VodAdminClassification(CreateVodAdminClassificationRequestDto requestDto,
                                  Vod vod) {
        super(requestDto.classificationInfo());
        this.vod = vod;
        this.vod.addAdminClassification(this);
    }

    public static VodAdminClassification createVodAdminClassification(CreateVodAdminClassificationRequestDto requestDto,
                                                                      Vod vod) {
        return VodAdminClassification.builder()
                .requestDto(requestDto)
                .vod(vod)
                .build();
    }
}
