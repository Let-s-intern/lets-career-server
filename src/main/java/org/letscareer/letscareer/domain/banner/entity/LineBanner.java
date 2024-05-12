package org.letscareer.letscareer.domain.banner.entity;

import jakarta.persistence.Column;
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
@DiscriminatorValue(value = BannerType.Values.LINE)
public class LineBanner extends Banner {
    @NotNull
    private String contents;

    @NotNull
    @Column(length = 7)
    private String colorCode;

    @NotNull
    @Column(length = 7)
    private String textColorCode;

    @Builder(access = AccessLevel.PRIVATE)
    private LineBanner(BannerType type, CreateBannerRequestDto createBannerRequestDto) {
        super(type, createBannerRequestDto);
        this.contents = createBannerRequestDto.contents();
        this.colorCode = createBannerRequestDto.colorCode();
        this.textColorCode = createBannerRequestDto.textColorCode();
    }

    public static LineBanner createLineBanner(BannerType type, CreateBannerRequestDto createBannerRequestDto) {
        return LineBanner.builder()
                .type(type)
                .createBannerRequestDto(createBannerRequestDto)
                .build();
    }
}
