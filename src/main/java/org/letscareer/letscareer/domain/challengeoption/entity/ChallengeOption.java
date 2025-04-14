package org.letscareer.letscareer.domain.challengeoption.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

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

    public void addChallengePriceOptionList(ChallengePriceOption challengePriceOption) {
        this.challengePriceOptionList.add(challengePriceOption);
    }
}
