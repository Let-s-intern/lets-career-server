package org.letscareer.letscareer.domain.banner.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.type.BannerType;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue(value = BannerType.Values.MAIN)
public class MainBanner extends Banner {
    @NotNull
    private String imgUrl;

    @Builder(access = AccessLevel.PRIVATE)
    private MainBanner(BannerType type, CreateBannerRequestDto createBannerRequestDto) {
        super(type, createBannerRequestDto);
        this.imgUrl = createBannerRequestDto.imgUrl();
    }

    public static MainBanner createMainBanner(BannerType type, CreateBannerRequestDto createBannerRequestDto) {
        return MainBanner.builder()
                .type(type)
                .createBannerRequestDto(createBannerRequestDto)
                .build();
    }
}
