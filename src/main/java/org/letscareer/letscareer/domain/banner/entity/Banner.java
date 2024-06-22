package org.letscareer.letscareer.domain.banner.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.request.UpdateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.type.BannerType;
import org.letscareer.letscareer.domain.banner.type.converter.BannerTypeConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;

import static org.letscareer.letscareer.global.common.utils.EntityUpdateValueUtils.updateValue;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn(name = "bannerType", discriminatorType = DiscriminatorType.INTEGER)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Banner extends BaseTimeEntity {
    @Id
    @Column(name = "banner_id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @NotNull
    @Convert(converter = BannerTypeConverter.class)
    private BannerType type;
    @NotNull
    @Column
    private String title;
    @NotNull
    private String link;
    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime endDate;
    @NotNull
    private Boolean isValid;
    @NotNull
    private Boolean isVisible;

    public Banner(BannerType type, CreateBannerRequestDto createBannerRequestDto) {
        this.type = type;
        this.title = createBannerRequestDto.title();
        this.link = createBannerRequestDto.link();
        this.startDate = createBannerRequestDto.startDate();
        this.endDate = createBannerRequestDto.endDate();
        this.isValid = true;
        this.isVisible = false;
    }

    protected void updateBanner(UpdateBannerRequestDto updateBannerRequestDto) {
        this.title = updateValue(this.title, updateBannerRequestDto.title());
        this.link = updateValue(this.link, updateBannerRequestDto.link());
        this.startDate = updateValue(this.startDate, updateBannerRequestDto.startDate());
        this.endDate = updateValue(this.endDate, updateBannerRequestDto.endDate());
        this.isValid = updateValue(this.isValid, updateBannerRequestDto.isValid());
        this.isVisible = updateValue(this.isVisible, updateBannerRequestDto.isVisible());
    }
}
