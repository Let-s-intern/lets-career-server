package org.letscareer.letscareer.domain.challengeoption.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.challengeoption.dto.request.CreateChallengeOptionRequestDto;
import org.letscareer.letscareer.domain.challengeoption.dto.request.UpdateChallengeOptionRequestDto;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

import static org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils.updateValue;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "challenge_option")
@Entity
public class ChallengeOption extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_option_id")
    private Long id;
    private String title;
    private String code;
    private Integer price;
    private Integer discountPrice;

    @OneToMany(mappedBy = "challengeOption", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ChallengePriceOption> challengePriceOptionList = new ArrayList<>();

    public static ChallengeOption createChallengeOption(CreateChallengeOptionRequestDto requestDto) {
        return ChallengeOption.builder()
                .title(requestDto.title())
                .code(requestDto.code())
                .price(requestDto.price())
                .discountPrice(requestDto.discountPrice())
                .build();
    }

    public void addChallengePriceOptionList(ChallengePriceOption challengePriceOption) {
        this.challengePriceOptionList.add(challengePriceOption);
    }

    public void updateChallengeOption(UpdateChallengeOptionRequestDto requestDto) {
        this.title = updateValue(this.title, requestDto.title());
        this.code = updateValue(this.code, requestDto.code());
        this.price = updateValue(this.price, requestDto.price());
        this.discountPrice = updateValue(this.discountPrice, requestDto.discountPrice());
    }
}
