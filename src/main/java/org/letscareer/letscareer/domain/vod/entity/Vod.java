package org.letscareer.letscareer.domain.vod.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.classification.entity.VodClassification;
import org.letscareer.letscareer.domain.vod.dto.request.CreateVodRequestDto;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

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
    private String stringJob;

    @OneToMany(mappedBy = "vod", cascade = CascadeType.ALL)
    @Builder.Default
    private List<VodClassification> classificationList = new ArrayList<>();

    public static Vod createVod(CreateVodRequestDto requestDto) {
        return Vod.builder()
                .title(requestDto.title())
                .shortDesc(requestDto.shortDesc())
                .thumbnail(requestDto.thumbnail())
                .stringJob(requestDto.stringJob())
                .build();
    }

    public void addClassification(VodClassification vodClassification) {
        this.classificationList.add(vodClassification);
    }
}
