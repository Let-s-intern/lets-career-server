package org.letscareer.letscareer.domain.challengeoption.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.price.entity.ChallengePrice;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "challenge_price_option")
@Entity
public class ChallengePriceOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_price_option_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_price_id")
    private ChallengePrice challengePrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_option_id")
    private ChallengeOption challengeOption;

    public static ChallengePriceOption createChallengePriceOption(ChallengePrice challengePrice,
                                                                  ChallengeOption challengeOption) {
        ChallengePriceOption challengePriceOption = ChallengePriceOption.builder()
                .challengePrice(challengePrice)
                .challengeOption(challengeOption)
                .build();
        challengePrice.addChallengePriceOptionList(challengePriceOption);
        challengeOption.addChallengePriceOptionList(challengePriceOption);
        return challengePriceOption;
    }
}
