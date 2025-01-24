package org.letscareer.letscareer.domain.vod.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.admincalssification.entity.VodAdminClassification;
import org.letscareer.letscareer.domain.classification.entity.VodClassification;
import org.letscareer.letscareer.domain.vod.dto.request.CreateVodRequestDto;
import org.letscareer.letscareer.domain.vod.dto.request.UpdateVodRequestDto;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

import static org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils.updateValue;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "vod")
@Entity
public class Vod extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vod_id")
    private Long id;
    private String title;
    private String shortDesc;
    private String thumbnail;
    private String job;
    private String link;
    @Builder.Default
    private Boolean isVisible = false;

    @OneToMany(mappedBy = "vod", cascade = CascadeType.ALL)
    @Builder.Default
    private List<VodClassification> classificationList = new ArrayList<>();

    @OneToMany(mappedBy = "vod", cascade = CascadeType.ALL)
    @Builder.Default
    private List<VodAdminClassification> adminClassificationList = new ArrayList<>();

    public static Vod createVod(CreateVodRequestDto requestDto) {
        return Vod.builder()
                .title(requestDto.title())
                .shortDesc(requestDto.shortDesc())
                .thumbnail(requestDto.thumbnail())
                .job(requestDto.job())
                .link(requestDto.link())
                .build();
    }

    public void updateVod(UpdateVodRequestDto requestDto) {
        this.title = updateValue(this.title, requestDto.title());
        this.shortDesc = updateValue(this.shortDesc, requestDto.shortDesc());
        this.thumbnail = updateValue(this.thumbnail, requestDto.thumbnail());
        this.job = updateValue(this.job, requestDto.job());
        this.link = updateValue(this.link, requestDto.link());
        this.isVisible = updateValue(this.isVisible, requestDto.isVisible());
    }

    public void addClassification(VodClassification vodClassification) {
        this.classificationList.add(vodClassification);
    }

    public void setInitClassifications() {
        this.classificationList = new ArrayList<>();
    }

    public void addAdminClassification(VodAdminClassification vodAdminClassification) {
        this.adminClassificationList.add(vodAdminClassification);
    }

    public void setInitAdminClassifications() {
        this.adminClassificationList = new ArrayList<>();
    }
}
